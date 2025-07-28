package com.example.product_server.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true, updatable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Product> products;



}


