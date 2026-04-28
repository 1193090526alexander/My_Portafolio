package com.company.inventory.Inventory.api.services.category.implementation;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.category.ICategoryRespository;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRespository categoryRespository;

    @Override
    public ResponseEntity<CategoryResposeRest> serch() {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        try {
            List<CategoryEntity> categoryEntities = categoryRespository.findAll();
            categoryResposeRest.getCategoryResponse().setCategory(categoryEntities);
            categoryResposeRest.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        }
        catch (Exception e) {
            categoryResposeRest.setMetadata("Respuesta nok", "-1", "Error al consultar");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.OK);
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
