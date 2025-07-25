package com.sun.microservices.product.dto.response;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProductResponse(String id, String name, String description, BigDecimal price) {
}
