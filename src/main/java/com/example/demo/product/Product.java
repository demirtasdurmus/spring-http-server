package com.example.demo.product;

public class Product {
  private int lastId = 1;
  private int id;
  private String name;

  public Product(String name) {
    this.name = name;
    this.id = lastId++;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
