package com.company.inventory.Inventory.api.services.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

     ResponseEntity<CategoryResposeRest> serch();
     ResponseEntity<CategoryResposeRest> findByName(String name);
     ResponseEntity<CategoryResposeRest> findById(Integer id);
     ResponseEntity<CategoryResposeRest> deleteCategoria(CategoryEntity category);
     ResponseEntity<CategoryResposeRest> saveCategory(CategoryEntity category);
     ResponseEntity<CategoryResposeRest> updateCategory(CategoryEntity category, Integer id);
}