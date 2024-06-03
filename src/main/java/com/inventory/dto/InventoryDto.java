package com.inventory.dto;

public class InventoryDto {

    private Long productId;

    private  String productName;

    private double costPrice;

    private double sellingPrice;

    private int minQuantity;

    private int quantity;

    private String productType;

    private boolean isActive;

    private String productCode;

    public InventoryDto() {
    }

    public InventoryDto(Long productId, String productName, double costPrice,
                        double sellingPrice, int minQuantity, int quantity, String productType, boolean isActive, String productCode) {
        this.productId = productId;
        this.productName = productName;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.minQuantity = minQuantity;
        this.quantity = quantity;
        this.productType = productType;
        this.isActive = isActive;
        this.productCode = productCode;
    }

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
                '}';
    }
}
