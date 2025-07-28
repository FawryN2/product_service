package com.example.product_server.service.impl;

import com.example.product_server.entity.Category;
import com.example.product_server.entity.Product;
import com.example.product_server.exception.ConflictException;
import com.example.product_server.mapper.ProductMapper;
import com.example.product_server.model.dto.product.ProductRequestDto;
import com.example.product_server.model.dto.product.ProductResponseDto;
import com.example.product_server.model.dto.product.ProductUpdateRequestDto;
import com.example.product_server.repository.CategoryRepository;
import com.example.product_server.repository.ProductRepository;
import com.example.product_server.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImp implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new EntityNotFoundException("Product with ID" + " " + productId + " " + "not found"));

        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDto addProduct(Long categoryId , ProductRequestDto productRequestDto){

        if(productRepository.existsProductBySku(productRequestDto.getSku())){
            throw new ConflictException("Product with sku" + " " + productRequestDto.getSku() + " " + "already exists");
        }

        Product product = productMapper.toEntity(productRequestDto);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID" + " " + categoryId + " " + "not found"));
        product.setCategory(category);

        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public String deleteProduct(Long productId) {
        if(!productRepository.existsById(productId)){
            throw new EntityNotFoundException("Product with ID" + " " + productId + "  " + "not found");
        }
        productRepository.deleteById(productId);
        return "Product with ID" + " " + productId + " " + "has been deleted";
    }

    @Override
    public ProductResponseDto updateProduct(Long productId , ProductUpdateRequestDto productUpdateRequestDto) {

        if(!productRepository.existsById(productId)){
           throw  new EntityNotFoundException("Product with ID" + " " + productId + " " + "not found");
        }
        Product product = productRepository.findBySku(productUpdateRequestDto.getSku())
                .orElseThrow( ()-> new  EntityNotFoundException("Product with sku" + productUpdateRequestDto.getSku()+ " " + "not found"));


        if (productUpdateRequestDto.getName() != null) product.setName(productUpdateRequestDto.getName());
        if (productUpdateRequestDto.getBrand() != null) product.setBrand(productUpdateRequestDto.getBrand());
        if (productUpdateRequestDto.getDescription() != null) product.setDescription(productUpdateRequestDto.getDescription());
        if (productUpdateRequestDto.getPrice() != null) product.setPrice(productUpdateRequestDto.getPrice());
        if (productUpdateRequestDto.getImageUrl() != null) product.setImageUrl(productUpdateRequestDto.getImageUrl());
        if (productUpdateRequestDto.getIsActive() != null) product.setIsActive(productUpdateRequestDto.getIsActive());


        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public String deleteAllProducts() {
        productRepository.deleteAll();
        return "All products has been deleted";
    }

    @Override
    public Boolean checkProductAvailability(String sku) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(()->  new EntityNotFoundException("Product with sku" + " " + sku + " " + "not found"));

        return product.getIsActive();
    }



}
