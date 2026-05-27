package com.company.inventory.Inventory.api.services.product;

import com.company.inventory.Inventory.api.model.CategoryEntity;
import com.company.inventory.Inventory.api.model.ProductEntity;
import com.company.inventory.Inventory.api.response.product.ProductoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {
    ResponseEntity<ProductoResponseRest> serch();
    ResponseEntity<ProductoResponseRest> findByNameProduct(String name);
    ResponseEntity<ProductoResponseRest> findById(Long id);
    ResponseEntity<ProductoResponseRest> deleteProduct(Long id);
    ResponseEntity<ProductoResponseRest> saveProduct(ProductEntity product, Integer idcategory);
    ResponseEntity<ProductoResponseRest> updateProduct(ProductEntity product,Integer categoryId, Long id);
}
