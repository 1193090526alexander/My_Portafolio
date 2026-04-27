package com.company.inventory.Inventory.services.category.implementation;

import com.company.inventory.Inventory.model.CategoryEntity;
import com.company.inventory.Inventory.repository.category.ICategoryRespository;
import com.company.inventory.Inventory.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRespository categoryRespository;
    @Override
    public ResponseEntity<CategoryResposeRest> serch() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResposeRest> findByName(String name) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResposeRest> findById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResposeRest> deleteCategoria(CategoryEntity category) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryResposeRest> saveCategory(CategoryEntity category) {
        return null;
    }
}
