package com.sun.microservices.product.dto.request;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price) {
}
