package com.li.micro.course.inventoryservice.controller;

import com.li.micro.course.inventoryservice.dto.InventoryResponse;
import com.li.micro.course.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 23.11.2023
 * Time: 11:29
 */
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {


    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("sku-code") List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }
}
