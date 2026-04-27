package com.company.inventory.Inventory.repository.category;

import com.company.inventory.Inventory.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRespository extends JpaRepository<CategoryEntity,Integer> {
}
