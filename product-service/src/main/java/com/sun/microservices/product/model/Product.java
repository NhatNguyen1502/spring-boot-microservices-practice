package com.sun.microservices.product.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    String id;
    String name;
    String description;
    BigDecimal price;
}