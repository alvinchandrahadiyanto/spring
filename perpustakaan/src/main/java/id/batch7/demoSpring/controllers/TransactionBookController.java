package id.batch7.demoSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.batch7.demoSpring.models.dto.request.TransactionBookRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.services.transactionBook.TransactionBookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactionBook")
public class TransactionBookController {
  @Autowired
  private TransactionBookService TransactionBookService;
  
  private ResponseData responseData;

  @PostMapping
  public ResponseEntity<?> borrowTransactionBook(@RequestBody @Valid TransactionBookRequest request) {
    try {
      responseData = TransactionBookService.borrowTransactionBookService(request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping
  public ResponseEntity<?> getCategories(@RequestParam(value = "status", defaultValue = "") Boolean status) {
    try {
      responseData = TransactionBookService.readTransactionBooksService(status);
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping("/{idTransactionBook}")
  public ResponseEntity<?> getTransactionBookById(@PathVariable("idTransactionBook") Integer id) throws Exception {
    responseData = TransactionBookService.readTransactionBookService(id);
    return ResponseEntity.ok().body(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateTransactionBookById(@PathVariable Integer id, @RequestBody TransactionBookRequest request) throws Exception {
    responseData = TransactionBookService.updateTransactionBookService(id, request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PutMapping("return/{id}")
  public ResponseEntity<?> returnTransactionBookService(@PathVariable Integer id) throws Exception {
    responseData = TransactionBookService.returnTransactionBookService(id);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}
