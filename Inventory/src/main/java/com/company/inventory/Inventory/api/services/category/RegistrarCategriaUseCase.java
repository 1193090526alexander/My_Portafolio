package com.company.inventory.Inventory.api.services.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.UseCase;
import org.springframework.http.ResponseEntity;

public interface RegistrarCategriaUseCase extends UseCase<CategoryEntity, ResponseEntity<CategoryResposeRest>> {
}
