package id.batch7.demoSpring.validators;

import java.util.Optional;

import org.springframework.stereotype.Service;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.models.entity.TransactionBook;

@Service
public class TransactionBookValidator {

  public void validateTransactionBookNotFound(Optional<TransactionBook> TransactionBookFind) throws Exception {
    if (TransactionBookFind.isEmpty()) {
      throw new CustomNotFoundException("TransactionBook is not found!");
    }
  }

  public void validateIsAlreadyDeleted(TransactionBook TransactionBook) throws Exception {
    if (TransactionBook.getIsDeleted()) {
      throw new Exception("TransactionBook is already deleted!");
    }
  }
}
