package com.example.demo.product;

import java.util.ArrayList;

public class ProductRepository {
  private ArrayList<Product> products;

  public ProductRepository() {
    products = new ArrayList<>();
    products.add(new Product("apple"));
  }

  public ArrayList<Product> getProducts() {
    return products;
  }
}
