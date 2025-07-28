package com.example.product_server.repository;

import com.example.product_server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Boolean existsProductBySku(String sku);

    Optional<Product > findBySku(String sku);
}
