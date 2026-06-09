package com.company.inventory.Inventory.api.controller.category;

import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.category.DeleteCategoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DeleteCategoryController {


    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public DeleteCategoryController(DeleteCategoryUseCase deleteCategoryUseCase) {
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }


    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResposeRest> execute(@PathVariable Integer id) {
       return deleteCategoryUseCase.execute(id);

    }
}
