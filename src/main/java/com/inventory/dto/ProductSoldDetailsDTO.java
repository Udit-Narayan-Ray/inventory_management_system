package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductSoldDetailsDTO {


    private Long productId;

    private int quantity;

    private  InvoiceProductDTO inventory;

    public ProductSoldDetailsDTO() {
    }

    public ProductSoldDetailsDTO(Long productId, int quantity, InvoiceProductDTO inventory) {
        this.productId = productId;
        this.quantity = quantity;
        this.inventory = inventory;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public InvoiceProductDTO getInventory() {
        return inventory;
    }

    public void setInventory(InvoiceProductDTO inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ProductSoldDetailsDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", inventory=" + inventory +
                '}';
    }
}
