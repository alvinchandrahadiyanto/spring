package id.batch7.demoSpring.validators;

import java.util.Optional;

import org.springframework.stereotype.Service;

import id.batch7.demoSpring.exceptions.custom.CustomNotFoundException;
import id.batch7.demoSpring.models.entity.User;

@Service
public class UserValidator {

  public void validateUserNotFound(Optional<User> UserFind) throws Exception {
    if (UserFind.isEmpty()) {
      throw new CustomNotFoundException("User is not found!");
    }
  }

  public void validateIsAlreadyDeleted(User User) throws Exception {
    if (User.getIsDeleted()) {
      throw new Exception("User is already deleted!");
    }
  }
}
