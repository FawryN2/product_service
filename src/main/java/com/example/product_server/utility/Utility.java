package com.example.product_server.utility;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Utility {

        public String generateSku(String category, String brand) {
            String catCode = category.substring(0, Math.min(category.length(), 3)).toUpperCase();
            String brandCode = brand.substring(0, Math.min(brand.length(), 3)).toUpperCase();
            String uniquePart = UUID.randomUUID().toString().substring(0, 8).toUpperCase(); // or productId if available

            return catCode + "-" + brandCode + "-" + uniquePart;
    }
}
