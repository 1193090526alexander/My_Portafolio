package com.company.inventory.Inventory.api.controller.category;


import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.RegistrarCategriaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200"})

public class SaveCategoryController {

    @Autowired
    private RegistrarCategriaUseCase registrarCategriaUseCase;

    @PostMapping("/categories")
    public ResponseEntity<CategoryResposeRest> execute(@RequestBody CategoryEntity categoryEntity){
        return  registrarCategriaUseCase.execute(categoryEntity);
    }
}
