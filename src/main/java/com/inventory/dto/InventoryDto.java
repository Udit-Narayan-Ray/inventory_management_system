package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class InventoryDto {

    private Long productId;

    private  String productName;

    private double costPrice;

    private double sellingPrice;

    private int minimumQuantity;

    @JsonIgnore
    private int minQuantity;

    private int quantity;

    private String productType;

    private boolean isActive;

    private String productCode;

    @JsonIgnore
    private SellerDto createdBy;


    private Long adminId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getProductCode() {
        return "PC-"+productId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    public SellerDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SellerDto createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "InventoryDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", minQuantity=" + minQuantity +
                ", quantity=" + quantity +
                ", productType='" + productType + '\'' +
                ", isActive=" + isActive +
                ", productCode='" + productCode + '\'' +
                ", adminId=" + adminId +
                '}';
    }


    public int getMinimumQuantity() {
        return minQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
        this.minQuantity = minimumQuantity;
    }



}
