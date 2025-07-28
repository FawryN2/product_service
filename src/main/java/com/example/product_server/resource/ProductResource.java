package com.example.product_server.resource;

import com.example.product_server.model.dto.product.ProductRequestDto;
import com.example.product_server.model.dto.product.ProductResponseDto;
import com.example.product_server.model.dto.product.ProductUpdateRequestDto;
import com.example.product_server.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductResource {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductResponseDto> getAllProducts(){
       return productService.getAllProducts();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{productId}")
    public ProductResponseDto getProduct(@PathVariable Long productId){
        return productService.getProduct(productId);
    }

    @PostMapping("/category/{categoryId}")
    public ResponseEntity<ProductResponseDto> addProduct(@PathVariable Long categoryId , @Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(categoryId,productRequestDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{productId}")
    public ProductResponseDto updateProduct(@PathVariable Long productId,@Valid  @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        return productService.updateProduct(productId , productUpdateRequestDto);
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public String deleteAllProducts(){
        return productService.deleteAllProducts();
    }


    @GetMapping("availability/{sku}")
    public ResponseEntity<Boolean> CheckProductAvailability(@PathVariable String sku){
        boolean isAvailable = productService.checkProductAvailability(sku);
        if(isAvailable){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
