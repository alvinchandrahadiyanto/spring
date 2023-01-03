package id.batch7.demoSpring.services.transactionBook;

import id.batch7.demoSpring.models.dto.request.TransactionBookRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;

public interface TransactionBookService {
  // kerangka methods untuk CRUD TransactionBook
  // Create
  ResponseData createTransactionBookService(TransactionBookRequest request) throws Exception;

  // Read
  ResponseData readTransactionBooksService(Boolean status);

  // By Id
  ResponseData readTransactionBookService(Integer id) throws Exception;

  // Update
  ResponseData updateTransactionBookService(Integer id, TransactionBookRequest request) throws Exception;
}
