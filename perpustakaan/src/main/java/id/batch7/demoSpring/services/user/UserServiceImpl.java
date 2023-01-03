package id.batch7.demoSpring.services.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.batch7.demoSpring.repositories.UserRepository;
import id.batch7.demoSpring.models.dto.request.UserRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.models.entity.User;
import id.batch7.demoSpring.validators.UserValidator;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private UserValidator UserValidator;

    private User User;
    private ResponseData responseData;
    private List<User> categories;

    @Override
    public ResponseData createUserService(UserRequest request) throws Exception {
        // TODO Auto-generated method stub
        User = new User();

        // Convert DTO to Entity
        User.setNameUser(request.getNameUser());

        // Save to repo
        UserRepository.save(User);

        // Response Data
        responseData = new ResponseData(HttpStatus.CREATED.value(), "success", User);
        return responseData;
    }

    @Override
    public ResponseData readUsersService(Boolean status) {
        // TODO Auto-generated method stub
        if (status == null) {
            categories = UserRepository.findAll();
          } else {
            categories = UserRepository.findByIsDeleted(status);
          }
  
        responseData = new ResponseData(200, "success", categories);
        return responseData;
    }

    @Override
    public ResponseData readUserService(Integer id) throws Exception {
        // Find User
        Optional<User> UserFind = UserRepository.findById(id);
        // Validate book if is not found
        UserValidator.validateUserNotFound(UserFind);

        // get User
        User = UserFind.get();
        // response data
        responseData = new ResponseData(200, "success", User);
        return responseData;
    }

    @Override
    public ResponseData updateUserService(Integer id, UserRequest request) throws Exception {
        // TODO Auto-generated method stub
        Optional<User> UserFind = UserRepository.findById(id);
        UserValidator.validateUserNotFound(UserFind);

        User = UserFind.get();
        User.setNameUser(request.getNameUser());

        UserRepository.save(User);

        responseData = new ResponseData(200, "User success updated", User);
        return responseData;
    }

    @Override
    public ResponseData deleteUserService(Integer id) throws Exception {
        // TODO Auto-generated method stub
        Optional<User> UserFind = UserRepository.findById(id);
        UserValidator.validateUserNotFound(UserFind);

        User = UserFind.get();
        UserValidator.validateIsAlreadyDeleted(User);

        User.setIsDeleted(true);

        UserRepository.save(User);

        responseData = new ResponseData(200, "success deleted", null);
        return responseData;
  }
    
}
