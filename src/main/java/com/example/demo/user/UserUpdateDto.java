package com.example.demo.user;

import jakarta.validation.constraints.NotBlank;

public class UserUpdateDto {
  @NotBlank(message = "First name is mandatory")
  private String firstName;

  @NotBlank(message = "Last name is mandatory")
  private String lastName;

  private Role role;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Role getRole() {
    return role;
  }
}
