package com.company.inventory.Inventory.api.controller.category;


import com.company.inventory.Inventory.api.model.ProductEntity;
import com.company.inventory.Inventory.api.response.category.CategoryResposeRest;
import com.company.inventory.Inventory.api.response.product.ProductoResponseRest;
import com.company.inventory.Inventory.api.services.product.IProductService;
import com.company.inventory.Inventory.api.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }

    public IProductService productService;


    @PostMapping("/products")
    public ResponseEntity<ProductoResponseRest> saveProduct(
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("category") Integer category) throws IOException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setPrice(price);
        productEntity.setQuantity(quantity);
        productEntity.setPicture(Util.compressZLib(picture.getBytes()));
        ResponseEntity<ProductoResponseRest> response = productService.saveProduct(productEntity, category);
        return response;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductoResponseRest>  searchCategory(@PathVariable Long id){
        ResponseEntity<ProductoResponseRest> responseEntity = productService.findById(id);
        return responseEntity;
    }

    @GetMapping("/product/name/{name}")
    public ResponseEntity<ProductoResponseRest>  searchCategory(@PathVariable String name){
        ResponseEntity<ProductoResponseRest> responseEntity = productService.findByNameProduct(name);
        return responseEntity;
    }
}
