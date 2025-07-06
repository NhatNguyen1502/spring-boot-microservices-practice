package com.sun.microservices.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory-service", url = "${inventory.service.url}")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET, path = "/api/inventories")
    boolean checkInventory(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") int quantity);
}
