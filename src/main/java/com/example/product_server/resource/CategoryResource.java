package com.example.product_server.resource;


import com.example.product_server.model.dto.ctegory.CategoryRequestDto;
import com.example.product_server.model.dto.ctegory.CategoryResponseDto;
import com.example.product_server.model.dto.product.ProductResponseDto;
import com.example.product_server.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryService categoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponseDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto getCategory(@PathVariable Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{categoryId}/products")
    public List<ProductResponseDto> getAllProductsForCategory(@PathVariable Long categoryId){
        return categoryService.getAllProductsForCategory(categoryId);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(categoryRequestDto));
    }


    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto updateCategory(@PathVariable Long categoryId , @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.updateCategory(categoryId,categoryRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{categoryID}")
    public String deleteCategory(@PathVariable Long categoryID) {
       return categoryService.deleteCategory(categoryID);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public String deleteAllCategories(){
        return categoryService.deleteAllCategories();
    }
}
