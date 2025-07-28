
package com.example.product_server.service;
import com.example.product_server.model.dto.ctegory.CategoryRequestDto;
import com.example.product_server.model.dto.ctegory.CategoryResponseDto;
import com.example.product_server.model.dto.product.ProductResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);
    List<CategoryResponseDto>getAllCategories();
    CategoryResponseDto getCategory(Long categoryId);
    CategoryResponseDto updateCategory(Long categoryId , CategoryRequestDto categoryRequestDto);
    List<ProductResponseDto> getAllProductsForCategory(Long categoryId);
    String deleteCategory(Long categoryId);
    String deleteAllCategories();

}
