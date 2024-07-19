package com.example.demo.product;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductRepository productRepo = new ProductRepository(new ArrayList<>());

  @GetMapping
  public ResponseEntity<ArrayList<Product>> getProducts() {
    return new ResponseEntity<>(productRepo.getProducts(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
    Product newProduct = productRepo.addProduct(productRequest.getName());
    return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
  }

}
