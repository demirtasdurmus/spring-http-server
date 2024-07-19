package com.example.demo.user;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepo = new UserRepository();

  @GetMapping
  public ArrayList<User> getUsers() {
    return userRepo.getUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable int id) {
    return userRepo.getUserById(id);
  }

  @PostMapping
  public User addUser(@RequestBody UserCreateRequest userCreateRequest) {
    User newUser = userRepo.addUser(userCreateRequest.getEmail(), userCreateRequest.getFirstName(),
        userCreateRequest.getFirstName());
    return newUser;
  }

  @PatchMapping("/{id}")
  public User updateUser(@PathVariable int id, @RequestBody UserUpdateRequest userUpdateRequest) {
    User updatedUser = userRepo.updateUser(id, userUpdateRequest.getFirstName(), userUpdateRequest.getLastName(),
        userUpdateRequest.getRole());
    return updatedUser;
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepo.deleteUser(id);
    return;
  }

}
