package com.example.demo.utils;

import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class ValidationErrorUtils {
  // private constructor to prevent instantiation
  private ValidationErrorUtils() {
  }

  public static String getErrorMessages(BindingResult bindingResult) {
    return bindingResult.getFieldErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .collect(Collectors.joining(", "));
  }
}
