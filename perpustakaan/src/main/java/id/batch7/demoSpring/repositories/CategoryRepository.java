package id.batch7.demoSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.batch7.demoSpring.models.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
  Category findByName(String categoryName);

  List<Category> findByIsDeleted(Boolean status);
}
