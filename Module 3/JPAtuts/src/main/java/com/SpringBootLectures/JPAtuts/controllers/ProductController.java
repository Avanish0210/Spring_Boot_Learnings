package com.SpringBootLectures.JPAtuts.controllers;

import com.SpringBootLectures.JPAtuts.entities.ProductEntity;
import com.SpringBootLectures.JPAtuts.repositories.ProductRepositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 5;

    private final ProductRepositories productRepositories;

    public ProductController(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber) {
        return productRepositories.findByTitleContainingIgnoreCase(
                title ,
                PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy))
        );



        //return productRepositories.findBy(Sort.by(Sort.Direction.DESC , sortBy , "price"));
        //return productRepositories.findBy(Sort.by(
              //  Sort.Order.desc(sortBy),
               // Sort.Order.asc("title")
        //));
        //Pageable pageable = PageRequest.of(
           //     pageNumber,
             //   PAGE_SIZE,
               // Sort.by(sortBy));
        //return productRepositories.findAll(pageable).getContent();
    }
}
