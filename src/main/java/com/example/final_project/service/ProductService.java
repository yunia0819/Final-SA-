package com.example.final_project.service;

import com.example.final_project.entity.dto.productDto;
import com.example.final_project.entity.product;
import com.example.final_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<productDto> getProductsByCategory(String category) {
        List<product> products = productRepository.findByCategory(category);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private productDto convertToDto(product product) {
        productDto dto = new productDto();
        dto.setproductId(product.getProductId());
        dto.setName(product.getName());
        dto.setPricePerDay(product.getPricePerDay());
        dto.setImageLink(product.getImageLink());
        dto.setRanks(product.getRanks());
        return dto;
    }
    public List<productDto> getProductsByCategories(List<String> categories) {
        List<product> products = productRepository.findByCategoryIn(categories);
        return products.stream().map(product -> new productDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPricePerDay(),
                product.getImageLink(),
                product.getRanks()
        )).collect(Collectors.toList());
    }

    public productDto getProductById(Long id) {
        Optional<product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product product = optionalProduct.get();
            return new productDto(
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPricePerDay(),
                    product.getImageLink(),
                    product.getRanks()
            );
        } else {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

}