package com.company.inventory.Inventory.api.services.category.implementation;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.RegistrarCategriaUseCase;
import com.company.inventory.Inventory.api.util.validator.CategoryValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaveCategoryUseCaseImpl implements RegistrarCategriaUseCase {

    private final CategoryValidator categoryValidator;
    private final com.company.inventory.Inventory.api.category.ICategoryRespository categoryRespository;

    public SaveCategoryUseCaseImpl(CategoryValidator categoryValidator, com.company.inventory.Inventory.api.category.ICategoryRespository categoryRespository) {
        this.categoryValidator = categoryValidator;
        this.categoryRespository = categoryRespository;
    }


    @Override
    public ResponseEntity<CategoryResposeRest> execute(CategoryEntity data) {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        List<CategoryEntity> listCategoryEntities =  new ArrayList<>();
        try {
            categoryValidator.validator(data);
            CategoryEntity categoryEntity = categoryRespository.save(data);
            if(categoryEntity != null) {
                listCategoryEntities.add(categoryEntity);
                categoryResposeRest.getCategoryResponse().setCategory(listCategoryEntities);
                categoryResposeRest.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            }
            else {
                categoryResposeRest.setMetadata("Respuesta nok", "-1", "Categoria no gurdada");
                return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.BAD_REQUEST);
            }
        }catch (IllegalArgumentException e) {

            categoryResposeRest.setMetadata(
                    "Respuesta nok",
                    "-1",
                    e.getMessage()
            );

            return new ResponseEntity<>(categoryResposeRest, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            categoryResposeRest.setMetadata("Respuesta nok", "-1", "Error al gurdar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(categoryResposeRest, HttpStatus.OK);
    }
}
