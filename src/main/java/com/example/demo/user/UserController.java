package com.example.demo.user;

import java.util.ArrayList;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.utils.ValidationErrorUtils;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepo = new UserRepository();

  @GetMapping
  public ResponseEntity<ArrayList<User>> getUsers() {
    return new ResponseEntity<>(userRepo.getUsers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable int id) {
    return new ResponseEntity<>(userRepo.getUserById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> addUser(@Valid @RequestBody UserCreateDto userCreateDto,
      BindingResult bindingResult) {
    // Check if there are validation errors
    if (bindingResult.hasErrors()) {
      String errorMessage = ValidationErrorUtils.getErrorMessages(bindingResult);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    // Create the new user and send the response
    User newUser = userRepo.addUser(userCreateDto.getEmail(), userCreateDto.getFirstName(),
        userCreateDto.getFirstName());

    return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateDto userUpdateRequest,
      BindingResult bindingResult) {
    // Check if there are validation errors
    if (bindingResult.hasErrors()) {
      String errorMessage = ValidationErrorUtils.getErrorMessages(bindingResult);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    // Update the user and send the response
    User updatedUser = userRepo.updateUser(id, userUpdateRequest.getFirstName(), userUpdateRequest.getLastName(),
        userUpdateRequest.getRole());

    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    userRepo.deleteUser(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
