package com.company.inventory.Inventory.api.controller.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class categoryRestController {

    @Autowired
    public ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResposeRest>  searchCategories(){
        ResponseEntity<CategoryResposeRest> responseEntity = categoryService.serch();
        return responseEntity;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResposeRest>  addCategory(@RequestBody CategoryEntity category){
        ResponseEntity<CategoryResposeRest> responseEntity = categoryService.saveCategory(category);
        return responseEntity;
    }
}
