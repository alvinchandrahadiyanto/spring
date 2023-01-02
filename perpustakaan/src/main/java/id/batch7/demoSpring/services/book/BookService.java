package id.batch7.demoSpring.services.book;

import id.batch7.demoSpring.models.dto.request.BookRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;

public interface BookService {
  // kerangka methods untuk CRUD Book
  // Create
  ResponseData createBookService(BookRequest request) throws Exception;

  // Read
  ResponseData readBooksService(Boolean status);

  // By Id
  ResponseData readBookService(Long id) throws Exception;

  // Update
  ResponseData updateBookService(Long id, BookRequest request) throws Exception;

  // Delete
  ResponseData deleteBookService(Long id) throws Exception;
}
