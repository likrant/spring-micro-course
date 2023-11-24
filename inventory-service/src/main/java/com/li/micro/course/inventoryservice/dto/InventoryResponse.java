package com.li.micro.course.inventoryservice.dto;

/**
 * Created by IntelliJ IDEA.
 * User: Li
 * Date: 24.11.2023
 * Time: 17:09
 */
public class InventoryResponse {

    private String skuCode;
    private boolean isInStock;

    public InventoryResponse() {
    }

    public InventoryResponse(String skuCode, boolean isInStock) {
        this.skuCode = skuCode;
        this.isInStock = isInStock;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
