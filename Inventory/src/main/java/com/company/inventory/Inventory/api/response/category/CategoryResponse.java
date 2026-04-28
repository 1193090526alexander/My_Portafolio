package com.company.inventory.Inventory.api.response.category;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<CategoryEntity> category;

}
