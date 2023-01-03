package id.batch7.demoSpring.services.transactionBook;

import java.util.List;
import java.util.Objects;
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
    private TransactionBookRepository TransactionBookRepository;

    @Autowired
    private TransactionBookValidator TransactionBookValidator;

    private BookRepository bookRepository;

    private UserRepository userRepository;

    private TransactionBook TransactionBook;
    private ResponseData responseData;
    private List<TransactionBook> categories;

    @Override
    public ResponseData createTransactionBookService(TransactionBookRequest request) throws Exception {
        // TODO Auto-generated method stub
        TransactionBook = new TransactionBook();

        // Convert DTO to Entity
        TransactionBook.setBookId(request.getBookId());
        TransactionBook.setUserId(request.getUserId());
        TransactionBook.setIsBorrowed(request.getIsBorrowed());

        // // Find category name
        // Optional<Book> book = bookRepository.findById((Long)request.getBookId());
        // Optional<User> user = userRepository.findById((Integer)request.getUserId());
        // if (Objects.isNull(book)) {
        //     throw new CustomNotFoundException("ID Book is not found!");
        // }
        // if (Objects.isNull(user)) {
        //     throw new CustomNotFoundException("ID User is not found!");
        // }

        // Save to repo
        TransactionBookRepository.save(TransactionBook);

        // Response Data
        responseData = new ResponseData(HttpStatus.CREATED.value(), "success", TransactionBook);
        return responseData;
    }

    @Override
    public ResponseData readTransactionBooksService(Boolean status) {
        // TODO Auto-generated method stub
        if (status == null) {
            categories = TransactionBookRepository.findAll();
          }
  
        responseData = new ResponseData(200, "success", categories);
        return responseData;
    }

    @Override
    public ResponseData readTransactionBookService(Integer id) throws Exception {
        // Find TransactionBook
        Optional<TransactionBook> TransactionBookFind = TransactionBookRepository.findById(id);
        // Validate book if is not found
        TransactionBookValidator.validateTransactionBookNotFound(TransactionBookFind);

        // get TransactionBook
        TransactionBook = TransactionBookFind.get();
        // response data
        responseData = new ResponseData(200, "success", TransactionBook);
        return responseData;
    }

    @Override
    public ResponseData updateTransactionBookService(Integer id, TransactionBookRequest request) throws Exception {
        // TODO Auto-generated method stub
        Optional<TransactionBook> TransactionBookFind = TransactionBookRepository.findById(id);
        TransactionBookValidator.validateTransactionBookNotFound(TransactionBookFind);

        TransactionBook = TransactionBookFind.get();
        TransactionBook.setBookId(request.getBookId());
        TransactionBook.setUserId(request.getUserId());
        TransactionBook.setIsBorrowed(request.getIsBorrowed());

        // // Find category name
        // Optional<Book> book = bookRepository.findById(request.getBookId());
        // Optional<User> user = userRepository.findById(request.getUserId());
        // if (Objects.isNull(book)) {
        //     throw new CustomNotFoundException("ID Book is not found!");
        // }
        // if (Objects.isNull(user)) {
        //     throw new CustomNotFoundException("ID User is not found!");
        // }

        TransactionBookRepository.save(TransactionBook);

        responseData = new ResponseData(200, "TransactionBook success updated", TransactionBook);
        return responseData;
    }
 
}
