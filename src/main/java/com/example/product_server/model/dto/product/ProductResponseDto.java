package com.example.product_server.model.dto.product;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String categoryName;
    private String name;
    private String sku;
    private String brand;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean isActive ;
}
