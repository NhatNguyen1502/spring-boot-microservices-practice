package com.sun.microservices.order.service;

import com.sun.microservices.order.client.InventoryClient;
import com.sun.microservices.order.dto.request.OrderRequest;
import com.sun.microservices.order.mapper.OrderMapper;
import com.sun.microservices.order.model.Order;
import com.sun.microservices.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;
    InventoryClient inventoryClient;

    /**
     * Places an order by converting the given OrderRequest to an Order entity
     * and saving it to the repository.
     *
     * @param orderRequest the order request containing order details
     */
    public void placeOrder(OrderRequest orderRequest) {
        boolean isInStock = inventoryClient.checkInventory(orderRequest.skuCode(), orderRequest.quantity());
        if (isInStock) {
            Order order = orderMapper.toOrder(orderRequest);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
