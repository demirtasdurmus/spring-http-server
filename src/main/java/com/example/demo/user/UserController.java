package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

  @Autowired
  private UserRepository userRepo;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    Optional<User> foundUser = userRepo.findById(id);

    if (foundUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID: " + id + " not found");
    }

    return new ResponseEntity<>(foundUser.get(), HttpStatus.CREATED);
  }

  @PostMapping
  public ResponseEntity<User> addUser(@Valid @RequestBody UserCreateDto userCreateDto,
      BindingResult bindingResult) {
    // Check if there are validation errors
    if (bindingResult.hasErrors()) {
      String errorMessage = ValidationErrorUtils.getErrorMessages(bindingResult);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    // Check if the user with this email already exists
    if (userRepo.findByEmail(userCreateDto.getEmail()) != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
    }

    // Create the new user and send the response
    User newUser = new User();
    newUser.setEmail(userCreateDto.getEmail());
    newUser.setFirstName(userCreateDto.getFirstName());
    newUser.setLastName(userCreateDto.getLastName());
    newUser.setRole(Role.USER); // Default role
    User savedUser = userRepo.save(newUser);

    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userUpdateDto,
      BindingResult bindingResult) {
    // Check if there are validation errors
    if (bindingResult.hasErrors()) {
      String errorMessage = ValidationErrorUtils.getErrorMessages(bindingResult);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
    }

    // Check if the user with this id exists
    Optional<User> existingUser = userRepo.findById(id);
    if (existingUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID: " + id + " not found");
    }

    // Update the user and send the response
    User user = existingUser.get();

    if (userUpdateDto.getFirstName() != null) {
      user.setFirstName(userUpdateDto.getFirstName());
    }

    if (userUpdateDto.getLastName() != null) {
      user.setLastName(userUpdateDto.getLastName());
    }
    if (userUpdateDto.getRole() != null) {
      user.setRole(userUpdateDto.getRole());
    }

    User updatedUser = userRepo.save(user);

    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    // Check if the user with this id exists
    Optional<User> existingUser = userRepo.findById(id);
    if (existingUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID: " + id + " not found");
    }

    // Delete user and send responase
    userRepo.delete(existingUser.get());

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
