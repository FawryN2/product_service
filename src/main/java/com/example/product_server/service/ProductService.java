package com.example.product_server.service;



import com.example.product_server.model.dto.product.ProductRequestDto;
import com.example.product_server.model.dto.product.ProductResponseDto;
import com.example.product_server.model.dto.product.ProductUpdateRequestDto;

import java.util.List;


public interface ProductService {
     List<ProductResponseDto> getAllProducts();
     ProductResponseDto getProduct(Long productId);
     ProductResponseDto addProduct(Long categoryId , ProductRequestDto productRequestDto);
     String deleteProduct(Long productId);
     ProductResponseDto updateProduct(Long productId , ProductUpdateRequestDto productUpdateRequestDto);
     String deleteAllProducts();
     Boolean checkProductAvailability(String sku);
}
