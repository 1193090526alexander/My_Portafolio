package com.company.inventory.Inventory.api.services.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.UseCasePatch;
import org.springframework.http.ResponseEntity;

public interface UpdateCategotyUseCase extends UseCasePatch<CategoryEntity, ResponseEntity<CategoryResposeRest>> {
}
