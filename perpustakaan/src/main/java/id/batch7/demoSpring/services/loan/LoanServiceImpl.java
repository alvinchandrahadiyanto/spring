package id.batch7.demoSpring.services.loan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.repositories.LoanRepository;
import id.batch7.demoSpring.models.dto.request.LoanRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.models.entity.Book;
import id.batch7.demoSpring.models.entity.User;
import id.batch7.demoSpring.repositories.BookRepository;
import id.batch7.demoSpring.repositories.UserRepository;
import id.batch7.demoSpring.models.entity.Loan;
import id.batch7.demoSpring.validators.BookValidator;
import id.batch7.demoSpring.validators.LoanValidator;

@Service
@Transactional
public class LoanServiceImpl implements LoanService{

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanValidator loanValidator;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookValidator bookValidator;

    private Loan loan;
    private ResponseData responseData;
    private List<Loan> loans;

    LocalDateTime dateTime = LocalDateTime.now();

    @Override
    public ResponseData borrowLoanService(LoanRequest request) throws Exception {
        // TODO Auto-generated method stub
        loan = new Loan();

        // Convert DTO to Entity
        loan.setBookId(request.getBookId());
        loan.setUserId(request.getUserId());

        Optional<Book> bookFind = bookRepository.findById(request.getBookId());
        bookValidator.validateBookNotFound(bookFind);

        Book book = bookFind.get();
        bookValidator.validateIsAlreadyDeleted(book);
        bookValidator.validateIsAlreadyBorrowed(book);

        book.setIsBorrowed(true);
        bookRepository.save(book);

        // Find category name
        Optional<Book> findBook = bookRepository.findById(request.getBookId());
        Optional<User> user = userRepository.findById(request.getUserId());
        if (findBook.isEmpty()) {
            throw new CustomNotFoundException("ID Book is not found!");
        }
        if (user.isEmpty()) {
            throw new CustomNotFoundException("ID User is not found!");
        }

        // Save to repo
        loanRepository.save(loan);

        // Response Data
        responseData = new ResponseData(HttpStatus.CREATED.value(), "success", loan);
        return responseData;
    }

    @Override
    public ResponseData readLoansService(Boolean status) {
        // TODO Auto-generated method stub
        if (status == null) {
            loans = loanRepository.findAll();
          }
  
        responseData = new ResponseData(200, "success", loans);
        return responseData;
    }

    @Override
    public ResponseData readLoanService(Integer id) throws Exception {
        // Find Loan
        Optional<Loan> LoanFind = loanRepository.findById(id);
        // Validate book if is not found
        loanValidator.validateLoanNotFound(LoanFind);

        // get Loan
        loan = LoanFind.get();
        // response data
        responseData = new ResponseData(200, "success", loan);
        return responseData;
    }

    @Override
    public ResponseData updateLoanService(Integer id, LoanRequest request) throws Exception {
        // TODO Auto-generated method stub
        Optional<Loan> LoanFind = loanRepository.findById(id);
        loanValidator.validateLoanNotFound(LoanFind);

        loan = LoanFind.get();
        loan.setBookId(request.getBookId());
        loan.setUserId(request.getUserId());

        // Find category name
        Optional<Book> book = bookRepository.findById(request.getBookId());
        Optional<User> user = userRepository.findById(request.getUserId());
        if (book.isEmpty()) {
            throw new CustomNotFoundException("ID Book is not found!");
        }
        if (user.isEmpty()) {
            throw new CustomNotFoundException("ID User is not found!");
        }

        loanRepository.save(loan);

        responseData = new ResponseData(200, "Loan success updated", loan);
        return responseData;
    }

    @Override
    public ResponseData returnLoanService(Integer id) throws Exception {
        // TODO Auto-generated method stub
        Optional<Loan> LoanFind = loanRepository.findById(id);
        loanValidator.validateLoanNotFound(LoanFind);

        loan = LoanFind.get();
        loan.setBookId(loan.getBookId());
        loan.setUserId(loan.getUserId());

        Optional<Book> bookFind = bookRepository.findById(loan.getBookId());
        bookValidator.validateBookNotFound(bookFind);

        Book book = bookFind.get();
        bookValidator.validateIsAlreadyDeleted(book);
        bookValidator.validateIsAlreadyBorrowed(book);

        book.setIsBorrowed(true);
        bookRepository.save(book);

        loan.setBorrowedDate(loan.getBorrowedDate());
        loan.setReturnedDate(dateTime.toString());

        loanRepository.save(loan);

        responseData = new ResponseData(200, "Loan success updated", loan);
        return responseData;
    }
 
}