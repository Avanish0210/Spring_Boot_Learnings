package com.learnDocker.product_service.controller;

import com.learnDocker.product_service.entity.Product;
import com.learnDocker.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/hello")
    public String hello(){
        return "hello from spring boot";
    }

    @PostMapping
    public String createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return "Product created";
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
