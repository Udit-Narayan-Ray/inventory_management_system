package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductSoldDetailsDTO {


    private Long productId;

    private String productCode;

    private int quantity;

    private String productName;

    private String productType;

    private double sellingPrice;

    private double totalPrice;

    @JsonIgnore
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

    public String getProductName() {
        if(this.inventory == null){return productName;}
        return this.inventory.getProductName();
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSellingPrice() {
        if(this.inventory == null){ return sellingPrice;}
        return this.inventory.getSellingPrice();
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getTotalPrice() {
        if(this.inventory == null){return totalPrice;}
        return (this.inventory.getSellingPrice()*quantity);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductType() {
        if(this.inventory == null) {return productType;}

        return this.inventory.getProductType();
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCode() {
        return this.inventory == null?productCode:"PC-"+this.inventory.getProductId();
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
