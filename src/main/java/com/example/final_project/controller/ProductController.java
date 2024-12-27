// ProductController.java
package com.example.final_project.controller;

import com.example.final_project.entity.dto.productDto;
import com.example.final_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/category/{category}")
    public List<productDto> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }
    @GetMapping("/categories")
    public List<productDto> getProductsByCategories(@RequestParam List<String> categories) {
        return productService.getProductsByCategories(categories);
    }

    @GetMapping("/{id}")
    public productDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

}