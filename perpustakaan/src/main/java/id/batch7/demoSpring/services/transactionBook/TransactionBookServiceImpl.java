package id.batch7.demoSpring.services.transactionBook;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.repositories.TransactionBookRepository;
import id.batch7.demoSpring.models.dto.request.TransactionBookRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.models.entity.Book;
import id.batch7.demoSpring.models.entity.User;
import id.batch7.demoSpring.repositories.BookRepository;
import id.batch7.demoSpring.repositories.UserRepository;
import id.batch7.demoSpring.models.entity.TransactionBook;
import id.batch7.demoSpring.validators.TransactionBookValidator;

@Service
@Transactional
public class TransactionBookServiceImpl implements TransactionBookService{

    @Autowired
    private TransactionBookRepository transactionBookRepository;

    @Autowired
    private TransactionBookValidator transactionBookValidator;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    private TransactionBook transactionBook;
    private ResponseData responseData;
    private List<TransactionBook> categories;

    LocalDateTime dateTime = LocalDateTime.now();

    @Override
    public ResponseData borrowTransactionBookService(TransactionBookRequest request) throws Exception {
        // TODO Auto-generated method stub
        transactionBook = new TransactionBook();

        // Convert DTO to Entity
        transactionBook.setBookId(request.getBookId());
        transactionBook.setUserId(request.getUserId());
        transactionBook.setIsBorrowed(true);
        transactionBook.setBorrowedDate(dateTime.toString());

        // Find category name
        Optional<Book> book = bookRepository.findById(request.getBookId());
        Optional<User> user = userRepository.findById(request.getUserId());
        if (book.isEmpty()) {
            throw new CustomNotFoundException("ID Book is not found!");
        }
        if (user.isEmpty()) {
            throw new CustomNotFoundException("ID User is not found!");
        }

        // Save to repo
        transactionBookRepository.save(transactionBook);

        // Response Data
        responseData = new ResponseData(HttpStatus.CREATED.value(), "success", transactionBook);
        return responseData;
    }

    @Override
    public ResponseData readTransactionBooksService(Boolean status) {
        // TODO Auto-generated method stub
        if (status == null) {
            categories = transactionBookRepository.findAll();
          }
  
        responseData = new ResponseData(200, "success", categories);
        return responseData;
    }

    @Override
    public ResponseData readTransactionBookService(Integer id) throws Exception {
        // Find TransactionBook
        Optional<TransactionBook> TransactionBookFind = transactionBookRepository.findById(id);
        // Validate book if is not found
        transactionBookValidator.validateTransactionBookNotFound(TransactionBookFind);

        // get TransactionBook
        transactionBook = TransactionBookFind.get();
        // response data
        responseData = new ResponseData(200, "success", transactionBook);
        return responseData;
    }

    @Override
    public ResponseData updateTransactionBookService(Integer id, TransactionBookRequest request) throws Exception {
        // TODO Auto-generated method stub
        Optional<TransactionBook> TransactionBookFind = transactionBookRepository.findById(id);
        transactionBookValidator.validateTransactionBookNotFound(TransactionBookFind);

        transactionBook = TransactionBookFind.get();
        transactionBook.setBookId(request.getBookId());
        transactionBook.setUserId(request.getUserId());

        // Find category name
        Optional<Book> book = bookRepository.findById(request.getBookId());
        Optional<User> user = userRepository.findById(request.getUserId());
        if (book.isEmpty()) {
            throw new CustomNotFoundException("ID Book is not found!");
        }
        if (user.isEmpty()) {
            throw new CustomNotFoundException("ID User is not found!");
        }

        transactionBookRepository.save(transactionBook);

        responseData = new ResponseData(200, "TransactionBook success updated", transactionBook);
        return responseData;
    }

    @Override
    public ResponseData returnTransactionBookService(Integer id) throws Exception {
        // TODO Auto-generated method stub
        Optional<TransactionBook> TransactionBookFind = transactionBookRepository.findById(id);
        transactionBookValidator.validateTransactionBookNotFound(TransactionBookFind);

        transactionBook = TransactionBookFind.get();
        transactionBook.setBookId(transactionBook.getBookId());
        transactionBook.setUserId(transactionBook.getUserId());
        transactionBook.setIsBorrowed(false);
        transactionBook.setBorrowedDate(transactionBook.getBorrowedDate());
        transactionBook.setReturnedDate(dateTime.toString());

        transactionBookRepository.save(transactionBook);

        responseData = new ResponseData(200, "TransactionBook success updated", transactionBook);
        return responseData;
    }
 
}