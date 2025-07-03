package com.sun.microservices.product;

import com.sun.microservices.product.dto.request.ProductRequest;
import com.sun.microservices.product.model.Product;
import com.sun.microservices.product.repository.ProductRepository;
import io.restassured.RestAssured;
import java.math.BigDecimal;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDbContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.5"));

	// Get the port number from the container
	@LocalServerPort
	private Integer port;

	@Autowired
	private ProductRepository productRepository;

	static {
		mongoDbContainer.start();
	}


	/**
     * Sets up the test environment before each test.
     * Configures RestAssured and seeds the database.
     */
    @BeforeEach
    void setUp() {
        // Set RestAssured base URI and port
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        // Clear existing data from the product repository
        productRepository.deleteAll();

        // Seed the database with initial data
        Product p1 = new Product(null, "Iphone 14", "Latest iPhone", BigDecimal.valueOf(1200));
        Product p2 = new Product(null, "Samsung S23", "Flagship Samsung", BigDecimal.valueOf(1000));
        productRepository.saveAll(List.of(p1, p2));
    }

	@Test
	void shouldCreateProduct() {
		ProductRequest productRequest = getProductRequest();

		RestAssured.given()
			.contentType("application/json")
			.body(productRequest)
			.when()
			.post("/api/products")
			.then()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo(productRequest.name()))
			.body("description", Matchers.equalTo(productRequest.description()))
			.body("price", Matchers.is(productRequest.price().intValueExact()))
			.log().ifValidationFails();
	}

	@Test
	void shouldGetAllProducts() {
		RestAssured.given()
			.when()
			.get("/api/products")
			.then()
			.statusCode(200)
			.body("size()", Matchers.is(2))
			.log().all();
	}

	private ProductRequest getProductRequest() {
		return new ProductRequest("iPhone 13", "iPhone 13", BigDecimal.valueOf(1200));
	}
}
