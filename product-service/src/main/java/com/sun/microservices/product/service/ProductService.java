package com.sun.microservices.product.service;

import com.sun.microservices.product.dto.request.ProductRequest;
import com.sun.microservices.product.dto.response.ProductResponse;
import com.sun.microservices.product.mapper.ProductMapper;
import com.sun.microservices.product.model.Product;
import com.sun.microservices.product.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    /**
     * Creates a product based on the given {@link ProductRequest}.
     *
     * @param productRequest the product to create
     */
    public Product createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
            .name(productRequest.name())
            .description(productRequest.description())
            .price(productRequest.price())
            .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return product;
    }

    /**
     * Retrieves all products from the database and maps them to {@link ProductResponse}.
     *
     * @return a list of {@link ProductResponse}
     */
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(productMapper::toProductResponse).toList();
    }
}
