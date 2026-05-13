package com.learnDocker.product_service.service;

import com.learnDocker.product_service.entity.Product;
import com.learnDocker.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(Product product) {
        productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
