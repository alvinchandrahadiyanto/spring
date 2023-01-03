package id.batch7.demoSpring.services.user;

import id.batch7.demoSpring.models.dto.request.UserRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;

public interface UserService {
  // kerangka methods untuk CRUD User
  // Create
  ResponseData createUserService(UserRequest request) throws Exception;

  // Read
  ResponseData readUsersService(Boolean status);

  // By Id
  ResponseData readUserService(Integer id) throws Exception;

  // Update
  ResponseData updateUserService(Integer id, UserRequest request) throws Exception;

  // Delete
  ResponseData deleteUserService(Integer id) throws Exception;
}
