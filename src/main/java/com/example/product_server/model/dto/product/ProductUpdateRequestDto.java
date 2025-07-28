package com.example.product_server.model.dto.product;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductUpdateRequestDto {
    @Column(updatable = false, unique = true,nullable = false)
    private String sku;
    private String name;
    private String brand;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean isActive;
}
