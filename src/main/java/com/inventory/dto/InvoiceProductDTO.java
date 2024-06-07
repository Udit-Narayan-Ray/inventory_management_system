package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class InvoiceProductDTO {

    private String productId;

    private  String productName;

    private double sellingPrice;

    private String productType;

    public InvoiceProductDTO() {
    }

    public InvoiceProductDTO(String productName, double sellingPrice, String productType) {
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "InvoiceProductDTO{" +
                "productName='" + productName + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", productType='" + productType + '\'' +
                '}';
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
