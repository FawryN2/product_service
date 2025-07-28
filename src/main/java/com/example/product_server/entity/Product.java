package com.example.product_server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false, updatable = false)
    private String sku;
    private String brand;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean isActive = true;

    @ManyToOne
    private Category category;

}
