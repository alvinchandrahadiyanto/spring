package id.batch7.demoSpring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.batch7.demoSpring.models.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    
}
