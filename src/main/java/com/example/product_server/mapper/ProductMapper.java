package com.example.product_server.mapper;


import com.example.product_server.entity.Product;
import com.example.product_server.model.dto.product.ProductRequestDto;
import com.example.product_server.model.dto.product.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductRequestDto dto);

    @Mapping(target = "categoryName" , source = "category.name")
    ProductResponseDto toDto(Product product);
}
