// ProductRepository.java
package com.example.final_project.repository;

import com.example.final_project.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<product, Long> {
    List<product> findByCategory(String category);
    List<product> findByCategoryIn(List<String> categories);


}
