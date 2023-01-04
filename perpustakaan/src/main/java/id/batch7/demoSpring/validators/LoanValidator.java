package id.batch7.demoSpring.validators;

import java.util.Optional;

import org.springframework.stereotype.Service;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.models.entity.Loan;

@Service
public class LoanValidator {

  public void validateLoanNotFound(Optional<Loan> LoanFind) throws Exception {
    if (LoanFind.isEmpty()) {
      throw new CustomNotFoundException("Loan is not found!");
    }
  }
}
