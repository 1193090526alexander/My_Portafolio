package com.company.inventory.Inventory.api.services.category.implementation;

import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.DeleteCategoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final com.company.inventory.Inventory.api.category.ICategoryRespository categoryRepository;

    public DeleteCategoryUseCaseImpl(com.company.inventory.Inventory.api.category.ICategoryRespository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ResponseEntity<CategoryResposeRest> execute(Integer data) {
        CategoryResposeRest response = new CategoryResposeRest();
        try {
            categoryRepository.deleteById(data);
            response.setMetadata("Respuesta ok", "00", "Categoria Eliminada");
        }
        catch (Exception e) {
            response.setMetadata("Respuesta nok", "-1", "Error al gurdar la categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResposeRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResposeRest>(response, HttpStatus.OK);
    }
}
