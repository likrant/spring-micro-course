package com.li.micro.course.productservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 20.11.2023
 * Time: 13:14
 */
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
