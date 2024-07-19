package com.example.demo.product;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductRepository {
  int lastId = 1;
  private ArrayList<Product> products;

  public ProductRepository(ArrayList<Product> products) {
    this.products = products;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public Product getProductById(int id) {
    return products.stream()
        .filter(product -> product.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
  }

  public Product addProduct(String name) {
    Optional<Product> existingProduct = products.stream()
        .filter(product -> product.getName().equalsIgnoreCase(name))
        .findFirst();

    if (existingProduct.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
    }

    int id = lastId++;

    Product newProduct = new Product(id, name);
    products.add(newProduct);
    return newProduct;
  }

  public Product updateProduct(int id, String name) {
    Product product = getProductById(id);

    product.setName(name);
    return product;
  }
}
