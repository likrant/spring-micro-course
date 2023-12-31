package com.li.micro.course.productservice.controller;

import com.li.micro.course.productservice.dto.ProductRequest;
import com.li.micro.course.productservice.dto.ProductResponse;
import com.li.micro.course.productservice.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 20.11.2023
 * Time: 13:12
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private static final Logger log = LogManager.getLogger();

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        log.info("call getAllProducts");
        return productService.getAllProducts();
    }
}
