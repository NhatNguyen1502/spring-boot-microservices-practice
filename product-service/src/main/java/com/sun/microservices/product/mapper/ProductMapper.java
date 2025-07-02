package com.sun.microservices.product.mapper;

import com.sun.microservices.product.dto.response.ProductResponse;
import com.sun.microservices.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    /**
     * Maps a {@link Product} to a {@link ProductResponse}.
     *
     * @param product the product to map
     * @return the mapped product response
     */
    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();
    }
}
