package com.sun.microservices.order;

import static org.hamcrest.MatcherAssert.assertThat;

import com.sun.microservices.order.dto.request.OrderRequest;
import io.restassured.RestAssured;
import java.math.BigDecimal;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    static {
        mySQLContainer.start();
    }

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void placeOrder_whenValidOrderRequest_thenOrderShouldBePlaced() {
        OrderRequest orderRequest = OrderRequest.builder()
            .orderNumber("123456")
            .skuCode("ABC123")
            .price(new BigDecimal("10.99"))
            .quantity(5)
            .build();

        var response = RestAssured.given()
            .contentType("application/json")
            .body(orderRequest)
            .post("api/orders")
            .then()
            .statusCode(201)
            .extract()
            .body().asString();
        assertThat(response, Matchers.is("Order placed successfully"));
    }
}
