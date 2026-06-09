package com.company.inventory.Inventory.api.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRespository extends JpaRepository<CategoryEntity,Integer> {
    Optional<CategoryEntity> findByNameContainingIgnoreCase(String name);
    boolean existsByNameContainingIgnoreCase(String name);
}
