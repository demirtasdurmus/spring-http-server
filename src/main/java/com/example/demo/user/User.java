package com.example.demo.user;

enum Role {
  USER, ADMIN
};

public class User {
  private int id;
  private String email;
  private String firstName;
  private String lastName;
  private Role role;

  public User(int id, String email, String firstName, String lastName) {
    this(id, email, firstName, lastName, Role.USER);
  }

  public User(int id, String email, String firstName, String lastName, Role role) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
