package com.example.demo.product;

import java.util.ArrayList;

public class ProductRepository {
  private ArrayList<Product> products;

  public ProductRepository(ArrayList<Product> products) {
    this.products = products;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public Product addProduct(String name) {
    Product newProduct = new Product(name);
    products.add(newProduct);
    return newProduct;
  }
}
