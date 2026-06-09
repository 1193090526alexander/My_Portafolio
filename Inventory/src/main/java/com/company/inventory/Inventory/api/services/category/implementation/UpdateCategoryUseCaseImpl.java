package com.company.inventory.Inventory.api.services.category.implementation;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.UpdateCategotyUseCase;
import com.company.inventory.Inventory.api.util.validator.CategoryValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateCategoryUseCaseImpl implements UpdateCategotyUseCase {

    private final com.company.inventory.Inventory.api.category.ICategoryRespository categoryRespository;
    private final CategoryValidator categoryValidator;

    public UpdateCategoryUseCaseImpl(com.company.inventory.Inventory.api.category.ICategoryRespository categoryRespository, CategoryValidator categoryValidator) {
        this.categoryRespository = categoryRespository;
        this.categoryValidator = categoryValidator;
    }

    @Override
    public ResponseEntity<CategoryResposeRest> execute(Integer id, CategoryEntity data) {
        CategoryResposeRest categoryResposeRest = new CategoryResposeRest();
        List<CategoryEntity> listCategoryEntities =  new ArrayList<>();
        try {
            categoryValidator.validarNombreEntidad(data.getName());
            categoryValidator.validarLongitudNombre(data.getName());
            Optional<CategoryEntity> categoryEntitySerch = categoryRespository.findById(id);

            if (categoryEntitySerch.isPresent()) {
                categoryEntitySerch.get().setName(data.getName());
                categoryEntitySerch.get().setDescription(data.getDescription());

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



