package com.company.inventory.Inventory.services.category;

import com.company.inventory.Inventory.model.CategoryEntity;
import com.company.inventory.Inventory.response.category.CategoryResposeRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    public ResponseEntity<CategoryResposeRest> serch();

    public ResponseEntity<CategoryResposeRest> findByName(String name);

    public ResponseEntity<CategoryResposeRest> findById(Integer id);

    public ResponseEntity<CategoryResposeRest> deleteCategoria(CategoryEntity category);

    public ResponseEntity<CategoryResposeRest> saveCategory(CategoryEntity category);
}