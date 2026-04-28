package com.company.inventory.Inventory.api.services.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    public ResponseEntity<CategoryResposeRest> serch();

    public ResponseEntity<CategoryResposeRest> findByName(String name);

    public ResponseEntity<CategoryResposeRest> findById(Integer id);

    public ResponseEntity<CategoryResposeRest> deleteCategoria(CategoryEntity category);

    public ResponseEntity<CategoryResposeRest> saveCategory(CategoryEntity category);
}