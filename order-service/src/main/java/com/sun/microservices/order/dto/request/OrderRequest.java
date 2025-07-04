package com.sun.microservices.order.dto.request;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record OrderRequest(String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
}
