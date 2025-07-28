package com.example.product_server.service.impl;

import com.example.product_server.entity.Category;
import com.example.product_server.exception.ConflictException;
import com.example.product_server.mapper.CategoryMapper;
import com.example.product_server.mapper.ProductMapper;
import com.example.product_server.model.dto.ctegory.CategoryRequestDto;
import com.example.product_server.model.dto.ctegory.CategoryResponseDto;
import com.example.product_server.model.dto.product.ProductResponseDto;
import com.example.product_server.repository.CategoryRepository;
import com.example.product_server.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    Category findCategory(Long categoryId){
         return categoryRepository.findById(categoryId)
                      .orElseThrow(()-> new EntityNotFoundException("Category with ID" + " " + categoryId + " " + "not found"));
    }

    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        String categoryName = categoryRequestDto.getName();
        if(categoryRepository.existsByNameIgnoreCase(categoryName)){
            throw new ConflictException("Category" + " " + categoryName + " " + "already exists");
        }
        Category newCategory = categoryMapper.toEntity(categoryRequestDto);
        categoryRepository.save(newCategory);
        return categoryMapper.toDto(newCategory);

    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) {
        Category existingCategory = this.findCategory(categoryId);
        return categoryMapper.toDto(existingCategory);
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category existsingCategory = this.findCategory(categoryId);
        String existingCategoryName = existsingCategory.getName();
        String requestedCategoryName = categoryRequestDto.getName();
        if(!existingCategoryName.equals(requestedCategoryName)){
            throw new ConflictException("There is not category with name" + " " + requestedCategoryName);
        }
        existsingCategory.setDescription(categoryRequestDto.getDescription());
        categoryRepository.save(existsingCategory);
        return categoryMapper.toDto(existsingCategory);
    }

    @Override
    public List<ProductResponseDto> getAllProductsForCategory(Long categoryId) {
        Category existingCategory = this.findCategory(categoryId);
        return existingCategory.getProducts()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category existingCategory = this.findCategory(categoryId);
        categoryRepository.delete(existingCategory);
        return "Category with ID" + " " + categoryId + " " + "has been deleted";
    }

    @Override
    public String deleteAllCategories() {
        categoryRepository.deleteAll();
        return "All categories has been deleted";
    }
}
