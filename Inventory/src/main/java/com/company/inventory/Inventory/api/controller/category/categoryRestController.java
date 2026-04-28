package com.company.inventory.Inventory.api.category;

import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
