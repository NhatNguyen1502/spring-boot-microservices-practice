package com.sun.microservices.order.mapper;

import com.sun.microservices.order.dto.request.OrderRequest;
import com.sun.microservices.order.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
            .orderNumber(orderRequest.orderNumber())
            .skuCode(orderRequest.skuCode())
            .price(orderRequest.price())
            .quantity(orderRequest.quantity())
            .build();
    }
}
