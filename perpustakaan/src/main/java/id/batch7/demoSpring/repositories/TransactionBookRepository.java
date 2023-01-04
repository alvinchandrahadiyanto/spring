package id.batch7.demoSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.batch7.demoSpring.models.entity.TransactionBook;

@Repository
public interface TransactionBookRepository extends JpaRepository<TransactionBook, Integer> {
    List<TransactionBook> findByIdAndIsBorrowed(Long id, Boolean isBorrowed);
}
