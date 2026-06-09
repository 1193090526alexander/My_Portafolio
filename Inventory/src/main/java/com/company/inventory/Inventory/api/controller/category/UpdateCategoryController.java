package com.company.inventory.Inventory.api.controller.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.RegistrarCategriaUseCase;
import com.company.inventory.Inventory.api.services.category.UpdateCategotyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200"})

public class UpdateCategoryController {


    @Autowired
    private UpdateCategotyUseCase updateCategotyUseCase;

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResposeRest> execute(@PathVariable Integer id, @RequestBody CategoryEntity categoryEntity){
        return  updateCategotyUseCase.execute(id, categoryEntity);
    }
}
