package com.example.product_server.model.dto.ctegory;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @NotBlank(message = "Name is required")
    @Column(updatable = false)
    private String name;

    private String description;
}
