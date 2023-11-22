package com.li.micro.course.productservice.service;

import com.li.micro.course.productservice.dto.ProductRequest;
import com.li.micro.course.productservice.dto.ProductResponse;
import com.li.micro.course.productservice.model.Product;
import com.li.micro.course.productservice.repository.ProductRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 20.11.2023
 * Time: 13:16
 */
@Service
public class ProductService {

    private static final Logger log = LogManager.getLogger();

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().setName(productRequest.getName())
                .setDescription(productRequest.getDescription())
                .setPrice(productRequest.getPrice())
                .build();
        productRepo.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepo.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
