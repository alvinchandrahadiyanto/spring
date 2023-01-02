package id.batch7.demoSpring.validators;

import java.util.Optional;

import org.springframework.stereotype.Service;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.models.entity.Category;

@Service
public class CategoryValidator {

  public void validateCategoryNotFound(Optional<Category> categoryFind) throws Exception {
    if (categoryFind.isEmpty()) {
      throw new CustomNotFoundException("Category is not found!");
    }
  }

  public void validateIsAlreadyDeleted(Category category) throws Exception {
    if (category.getIsDeleted()) {
      throw new Exception("Category is already deleted!");
    }
  }
}
