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

import id.batch7.demoSpring.models.dto.request.LoanRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.services.loan.LoanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/loan")
public class LoanController {
  @Autowired
  private LoanService LoanService;
  
  private ResponseData responseData;

  @PostMapping
  public ResponseEntity<?> borrowLoan(@RequestBody @Valid LoanRequest request) {
    try {
      responseData = LoanService.borrowLoanService(request);
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
      responseData = LoanService.readLoansService(status);
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping("/{idLoan}")
  public ResponseEntity<?> getLoanById(@PathVariable("idLoan") Integer id) throws Exception {
    responseData = LoanService.readLoanService(id);
    return ResponseEntity.ok().body(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateLoanById(@PathVariable Integer id, @RequestBody LoanRequest request) throws Exception {
    responseData = LoanService.updateLoanService(id, request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PutMapping("return/{id}")
  public ResponseEntity<?> returnLoanService(@PathVariable Integer id) throws Exception {
    responseData = LoanService.returnLoanService(id);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}
