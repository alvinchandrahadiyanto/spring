package id.batch7.demoSpring.services.loan;

import id.batch7.demoSpring.models.dto.request.LoanRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;

public interface LoanService {
  // kerangka methods untuk CRUD Loan
  // Create
  ResponseData borrowLoanService(LoanRequest request) throws Exception;

  // Read
  ResponseData readLoansService(Boolean status);

  // By Id
  ResponseData readLoanService(Integer id) throws Exception;

  // Update
  ResponseData updateLoanService(Integer id, LoanRequest request) throws Exception;

  // Return
  ResponseData returnLoanService(Integer id) throws Exception;
}
