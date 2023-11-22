package com.li.micro.course.productservice.repository;

import com.li.micro.course.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 20.11.2023
 * Time: 13:05
 */
@Repository
public interface ProductRepo extends MongoRepository<Product, String> {


}
