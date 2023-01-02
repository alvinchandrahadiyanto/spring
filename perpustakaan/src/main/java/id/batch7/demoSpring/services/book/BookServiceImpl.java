package id.batch7.demoSpring.services.book;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.models.dto.request.BookRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.models.entity.Book;
import id.batch7.demoSpring.models.entity.Category;
import id.batch7.demoSpring.repositories.BookRepository;
import id.batch7.demoSpring.repositories.CategoryRepository;
import id.batch7.demoSpring.validators.BookValidator;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private BookValidator bookValidator;

  private Book book;
  private ResponseData responseData;
  private List<Book> books;

  @Override
  public ResponseData createBookService(BookRequest request) throws Exception {
    // TODO Auto-generated method stub
    // if (request.getJudulBuku() == null || request.getPenerbit() == null ||
    // request.getPenulis() == null) {
    // responseData = new ResponseData(400, "Bad Request.", null);
    // } else {
    // Instance book
    book = new Book();

    // Convert DTO to Entity
    book.setAuthor(request.getPenulis());
    book.setPublisher(request.getPenerbit());
    book.setTitle(request.getJudulBuku());

    // Find category name
    Category category = categoryRepository.findByName(request.getCategoryName());
    if (Objects.isNull(category)) {
      throw new CustomNotFoundException("Category is not found!");
    }

    book.setCategory(category);

    // Save to repo
    bookRepository.save(book);

    // Response Data
    responseData = new ResponseData(HttpStatus.CREATED.value(), "success", book);
    // }

    return responseData;
  }

  @Override
  public ResponseData readBooksService(Boolean status) {
    // TODO Auto-generated method stub
    if (status == null) {
      books = bookRepository.findAll();
    } else {
      books = bookRepository.findByIsDeleted(status);
    }

    responseData = new ResponseData(200, "success", books);
    return responseData;
  }

  @Override
  public ResponseData readBookService(Long id) throws Exception {
    // TODO Auto-generated method stub
    // Find book
    Optional<Book> bookFind = bookRepository.findById(id);
    // Validate book if is not found
    bookValidator.validateBookNotFound(bookFind);

    // get book
    book = bookFind.get();
    // response data
    responseData = new ResponseData(200, "success", book);
    return responseData;
  }

  @Override
  public ResponseData updateBookService(Long id, BookRequest request) throws Exception {
    // TODO Auto-generated method stub
    Optional<Book> bookFind = bookRepository.findById(id);
    bookValidator.validateBookNotFound(bookFind);

    book = bookFind.get();
    book.setAuthor(request.getPenulis());
    book.setPublisher(request.getPenerbit());
    book.setTitle(request.getJudulBuku());

    // Find category
    Category category = categoryRepository.findByName(request.getCategoryName());
    if (Objects.isNull(category)) {
      throw new CustomNotFoundException("Category is not found!");
    }

    book.setCategory(category);

    bookRepository.save(book);

    responseData = new ResponseData(200, "success updated", book);
    return responseData;
  }

  @Override
  public ResponseData deleteBookService(Long id) throws Exception {
    // TODO Auto-generated method stub
    Optional<Book> bookFind = bookRepository.findById(id);
    bookValidator.validateBookNotFound(bookFind);

    book = bookFind.get();
    bookValidator.validateIsAlreadyDeleted(book);

    book.setIsDeleted(true);

    bookRepository.save(book);

    responseData = new ResponseData(200, "success deleted", null);
    return responseData;
  }

}
