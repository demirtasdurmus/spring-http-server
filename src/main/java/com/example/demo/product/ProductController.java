package com.example.demo.product;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @RequestMapping(value = "/products")
  public ResponseEntity<ArrayList<Product>> getProducts() {
    ProductRepository productRepo = new ProductRepository();
    return new ResponseEntity<>(productRepo.getProducts(), HttpStatus.OK);
  }
}
