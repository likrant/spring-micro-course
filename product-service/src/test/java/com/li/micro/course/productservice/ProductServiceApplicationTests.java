package com.li.micro.course.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.micro.course.productservice.dto.ProductRequest;
import com.li.micro.course.productservice.repository.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepo productRepo;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestJson = objectMapper.writeValueAsString(productRequest);
        mocMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Assertions.assertEquals(1, productRepo.findAll().size());
    }

    private ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("iPhone 12");
        productRequest.setDescription("Apple iPhone 12");
        productRequest.setPrice(BigDecimal.valueOf(799.99));
        return productRequest;
    }


}
