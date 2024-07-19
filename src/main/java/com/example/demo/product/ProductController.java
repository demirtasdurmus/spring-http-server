package com.example.demo.product;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductRepository productRepo = new ProductRepository();

  @GetMapping
  public ResponseEntity<ArrayList<Product>> getProducts() {
    return new ResponseEntity<>(productRepo.getProducts(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable int id) {
    Product product = productRepo.getProductById(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
    Product newProduct = productRepo.addProduct(productRequest.getName());
    return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody ProductRequest productRequest) {
    Product updatedProduct = productRepo.updateProduct(id, productRequest.getName());
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
    productRepo.deleteProduct(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
