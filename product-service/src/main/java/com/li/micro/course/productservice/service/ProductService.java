package com.li.micro.course.productservice.service;

import com.li.micro.course.productservice.dto.ProductRequest;
import com.li.micro.course.productservice.model.Product;
import com.li.micro.course.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 20.11.2023
 * Time: 13:16
 */
@Service
public class ProductService {

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
    }
}
