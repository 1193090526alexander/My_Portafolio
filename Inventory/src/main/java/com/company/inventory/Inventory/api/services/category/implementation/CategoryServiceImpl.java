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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<CategoryResposeRest> findByNamecategory(String name) {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        List<CategoryEntity> listCategoryEntities = new ArrayList<>();
        try {
            Optional<CategoryEntity> categoryEntity = categoryRespository.findByNameContainingIgnoreCase(name);
            if (categoryEntity.isPresent()) {
                listCategoryEntities.add(categoryEntity.get());
                categoryResposeRest.getCategoryResponse().setCategory(listCategoryEntities);
                categoryResposeRest.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
            else {
                categoryResposeRest.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception e) {
            categoryResposeRest.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CategoryResposeRest> findById(Integer id) {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        List<CategoryEntity> listCategoryEntities = new ArrayList<>();
        try {
            Optional<CategoryEntity> categoryEntity = categoryRespository.findById(id);
            if (categoryEntity.isPresent()) {
                listCategoryEntities.add(categoryEntity.get());
                categoryResposeRest.getCategoryResponse().setCategory(listCategoryEntities);
                categoryResposeRest.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
            else {
                categoryResposeRest.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception e) {
            categoryResposeRest.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResposeRest> deleteCategoria(Integer id) {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        try {
            categoryRespository.deleteById(id);
            categoryResposeRest.setMetadata("Respuesta ok", "00", "Categoria Eliminada");
        }
        catch (Exception e) {
            categoryResposeRest.setMetadata("Respuesta nok", "-1", "Error al gurdar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<CategoryResposeRest> saveCategory(CategoryEntity category) {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        List<CategoryEntity> listCategoryEntities =  new ArrayList<>();
        try {
            CategoryEntity categoryEntity = categoryRespository.save(category);
            if(categoryEntity != null) {
                listCategoryEntities.add(categoryEntity);
                categoryResposeRest.getCategoryResponse().setCategory(listCategoryEntities);
                categoryResposeRest.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
            else {
                categoryResposeRest.setMetadata("Respuesta nok", "-1", "Categoria no gurdada");
                return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            categoryResposeRest.setMetadata("Respuesta nok", "-1", "Error al gurdar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResposeRest> updateCategory(CategoryEntity category, Integer id) {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        List<CategoryEntity> listCategoryEntities =  new ArrayList<>();
        try {

            Optional<CategoryEntity> categoryEntitySerch = categoryRespository.findById(id);

            if (categoryEntitySerch.isPresent()) {
                categoryEntitySerch.get().setName(category.getName());
                categoryEntitySerch.get().setDescription(category.getDescription());

                CategoryEntity categoryEntityUpdate = categoryRespository.save(categoryEntitySerch.get());
                if(categoryEntityUpdate!=null) {
                    listCategoryEntities.add(categoryEntityUpdate);
                    categoryResposeRest.getCategoryResponse().setCategory(listCategoryEntities);
                    categoryResposeRest.setMetadata("Respuesta ok", "00", "Categoria Actulizada");
                }
                else {
                    categoryResposeRest.setMetadata("Respuesta nok", "-1", "Categoria no actulizada");
                    return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.BAD_REQUEST);
                }
            }
            else {
                categoryResposeRest.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            categoryResposeRest.setMetadata("Respuesta nok", "-1", "Error al gurdar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.OK);
    }
}
