package com.company.inventory.Inventory.api.repository;

import com.company.inventory.Inventory.api.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductoRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
