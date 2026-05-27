package com.company.inventory.Inventory.api.response.product;

import com.company.inventory.Inventory.api.model.ProductEntity;
import lombok.Data;

import java.util.List;
@Data
public class ProductoResponse {
    private List<ProductEntity> productEntities;
}
