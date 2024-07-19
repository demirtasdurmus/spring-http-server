package com.example.demo.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserRepository {
  private int lastId = 1;
  ArrayList<User> users = new ArrayList<>();

  public ArrayList<User> getUsers() {
    return users;
  }

  public User getUserById(int id) {
    return users.stream()
        .filter(user -> user.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  public User addUser(String email, String firstName, String lastName) {
    // Check if the user with the same email already exists
    Optional<User> existingUser = users.stream()
        .filter(user -> user.getEmail().equalsIgnoreCase(email))
        .findFirst();

    if (existingUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
    }

    // Create the new user
    int id = lastId++;
    User newUser = new User(id, email, firstName, lastName);
    users.add(newUser);
    return newUser;
  }

  public User updateUser(int id, String firstName, String lastName, Role role) {
    User foundUser = getUserById(id);

    if (firstName != null) {
      foundUser.setFirstName(firstName);
    }

    if (lastName != null) {
      foundUser.setLastName(lastName);
    }

    if (role != null) {
      foundUser.setRole(role);
    }

    return foundUser;
  }

  public void deleteUser(int id) {
    Iterator<User> iterator = users.iterator();

    while (iterator.hasNext()) {
      User user = iterator.next();
      if (user.getId() == id) {
        iterator.remove();
        return;
      }
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
  }

}
