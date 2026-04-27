package com.company.inventory.Inventory.response.category;

import com.company.inventory.Inventory.model.CategoryEntity;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<CategoryEntity> category;

}
