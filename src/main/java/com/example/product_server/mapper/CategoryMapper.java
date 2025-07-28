package com.example.product_server.mapper;

import com.example.product_server.entity.Category;
import com.example.product_server.model.dto.ctegory.CategoryRequestDto;
import com.example.product_server.model.dto.ctegory.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "products" , ignore = true)
    Category toEntity(CategoryRequestDto categoryRequestDto);

//    @Mapping(target = "products" , ignore = true)
    CategoryResponseDto toDto(Category category);
}
