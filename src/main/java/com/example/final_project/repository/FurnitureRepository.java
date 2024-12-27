package com.example.final_project.repository;

import com.example.final_project.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    Optional<Furniture> findByName(String name);
    void deleteByName(String furnitureName);
}

