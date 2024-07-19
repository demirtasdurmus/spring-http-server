package com.example.demo.user;

import java.util.ArrayList;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

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
  public User addUser(@Valid @RequestBody UserCreateRequest userCreateRequest, BindingResult bindingResult) {
    // Check if there are validation errors
    if (bindingResult.hasErrors()) {
      String errorMessage = bindingResult.getFieldErrors()
          .stream()
          .map(error -> error.getDefaultMessage())
          .collect(Collectors.joining(", "));

      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

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
