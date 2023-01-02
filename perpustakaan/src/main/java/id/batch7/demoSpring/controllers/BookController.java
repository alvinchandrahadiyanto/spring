package id.batch7.demoSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.batch7.demoSpring.models.dto.request.BookRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.services.book.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;

  private ResponseData responseData;

  @PostMapping
  public ResponseEntity<?> addBook(@RequestBody @Valid BookRequest request) {
    try {
      responseData = bookService.createBookService(request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping
  // /books -> findall
  // /books?status=false -> findall dengan is deleted false
  // /books?status=true -> findall dengan is deleted true
  public ResponseEntity<?> getBooks(@RequestParam(value = "status", defaultValue = "") Boolean status) {
    try {
      responseData = bookService.readBooksService(status);
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping("/{idBook}")
  public ResponseEntity<?> getBookById(@PathVariable("idBook") Long id) throws Exception {
    // try {
    responseData = bookService.readBookService(id);
    return ResponseEntity.ok().body(responseData);
    // } catch (Exception e) {
    // // TODO: handle exception
    // e.printStackTrace();
    // responseData = new ResponseData(500, e.getMessage(), null);
    // return ResponseEntity.status(responseData.getStatus()).body(responseData);
    // }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateBookById(@PathVariable Long id, @RequestBody BookRequest request) throws Exception {
    // try {
    responseData = bookService.updateBookService(id, request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
    // } catch (Exception e) {
    // // TODO: handle exception
    // e.printStackTrace();
    // responseData = new ResponseData(500, e.getMessage(), null);
    // return ResponseEntity.status(responseData.getStatus()).body(responseData);
    // }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable Long id) throws Exception {
    // try {
    responseData = bookService.deleteBookService(id);
    return ResponseEntity.ok().body(responseData);
    // } catch (Exception e) {
    // // TODO: handle exception
    // e.printStackTrace();
    // responseData = new ResponseData(500, e.getMessage(), null);
    // return ResponseEntity.status(responseData.getStatus()).body(responseData);
    // }
  }
}
