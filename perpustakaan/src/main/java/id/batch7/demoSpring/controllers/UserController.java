package id.batch7.demoSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.batch7.demoSpring.models.dto.request.UserRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.services.user.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Users")
public class UserController {
  @Autowired
  private UserService UserService;
  
  private ResponseData responseData;

  @PostMapping
  public ResponseEntity<?> addUser(@RequestBody @Valid UserRequest request) {
    try {
      responseData = UserService.createUserService(request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping
  public ResponseEntity<?> getUsers(@RequestParam(value = "status", defaultValue = "") Boolean status) {
    try {
      responseData = UserService.readUsersService(status);
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping("/{idUser}")
  public ResponseEntity<?> getUserById(@PathVariable("idUser") Integer id) throws Exception {
    responseData = UserService.readUserService(id);
    return ResponseEntity.ok().body(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUserById(@PathVariable Integer id, @RequestBody UserRequest request) throws Exception {
    responseData = UserService.updateUserService(id, request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Integer id) throws Exception {
    responseData = UserService.deleteUserService(id);
    return ResponseEntity.ok().body(responseData);
  }
}
