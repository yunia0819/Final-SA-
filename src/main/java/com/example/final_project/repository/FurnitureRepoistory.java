package com.example.final_project.repository;

import com.example.final_project.entity.Furniture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    Optional<Furniture> findByName(String name);
}
