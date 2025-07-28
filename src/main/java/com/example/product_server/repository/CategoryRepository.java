package com.example.product_server.repository;

import com.example.product_server.entity.Category;
import com.example.product_server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Boolean existsByNameIgnoreCase(String name);
}
