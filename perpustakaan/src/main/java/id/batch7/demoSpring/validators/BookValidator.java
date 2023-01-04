package id.batch7.demoSpring.validators;

import java.util.Optional;

import org.springframework.stereotype.Service;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.models.entity.Book;

@Service
public class BookValidator {

  public void validateBookNotFound(Optional<Book> bookFind) throws Exception {
    if (bookFind.isEmpty()) {
      throw new CustomNotFoundException("Book is not found!");
    }
  }

  public void validateIsAlreadyDeleted(Book book) throws Exception {
    if (book.getIsDeleted()) {
      throw new CustomNotFoundException("Book is already deleted!");
    }
  }

  public void validateIsAlreadyBorrowed(Book book) throws Exception {
    if (book.getIsBorrowed()) {
      throw new CustomNotFoundException("Book is already borrowed!");
    }
  }
}
