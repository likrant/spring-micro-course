package com.li.micro.course.inventoryservice.service;

import com.li.micro.course.inventoryservice.dto.InventoryResponse;
import com.li.micro.course.inventoryservice.repository.InventoryRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 23.11.2023
 * Time: 11:31
 */
@Service
public class InventoryService {

    private static final Logger log = LogManager.getLogger();
    private final InventoryRepo inventoryRepo;

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        return inventoryRepo.findBySkuCodeIn(skuCodes)
                .stream()
                .map(inventory ->
                        new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0)).toList();
    }
}
