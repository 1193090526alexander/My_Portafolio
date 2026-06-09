package com.company.inventory.Inventory.api.services.category;

import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.services.UseCase;
import org.springframework.http.ResponseEntity;

public interface DeleteCategoryUseCase extends UseCase<Integer, ResponseEntity<CategoryResposeRest>> {
}
