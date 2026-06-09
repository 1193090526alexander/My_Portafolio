package com.company.inventory.Inventory.api.util.validator;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {

    @Autowired
    private com.company.inventory.Inventory.api.category.ICategoryRespository  categoryRespository;



    public void validator(CategoryEntity category){
        validarEntidad(category);
        validarNombreEntidad(category.getName());
        validarCategoryNoExist(category.getName());
        validarLongitudNombre(category.getName());
    }

    public void validarEntidad(CategoryEntity category){
        if (category == null){
            throw new IllegalArgumentException("El categorio no puede ser nulo");

        }
    }
    public void validarNombreEntidad(String name){
        if (name == null){
            throw  new IllegalArgumentException("El nombre es obligatorio");
        }
    }
    public void validarCategoryNoExist(String name){
        if (categoryRespository.existsByNameContainingIgnoreCase(name.trim())){
            throw new IllegalArgumentException("ya existe una categoria con ese nombre");
        }
    }
    public void validarLongitudNombre(String name) {
        if(name.length()<3){
            throw new IllegalArgumentException("el nombre no puede ser menor que 3 letras");
        }
    }

    

}
