package com.company.inventory.Inventory.api.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRespository extends JpaRepository<CategoryEntity,Integer> {
}
