package com.example.demo.product;

public class Product {
  private int last_id = 1;
  private int id;
  private String name;

  public Product(String name) {
    this.name = name;
    this.id = last_id++;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
