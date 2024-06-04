package com.inventory.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private  String productName;

    private double costPrice;

    private double sellingPrice;

    private int minQuantity;

    private int quantity;

    private String productType;

    private boolean isActive;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller createdBy;

    @Column(nullable = false)
    private Date createdAt;

    private Date updateAt;

    public Inventory() {
    }

    public Inventory(String productName, Long productId, double costPrice, double sellingPrice,
                     int minQuantity, int quantity, String productType,
                     boolean isActive, Seller createdBy, Date createdAt, Date updateAt) {
        this.productName = productName;
        this.productId = productId;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.minQuantity = minQuantity;
        this.quantity = quantity;
        this.productType = productType;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
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

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Seller getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Seller createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", minQuantity=" + minQuantity +
                ", quantity=" + quantity +
                ", productType='" + productType + '\'' +
                ", isActive=" + isActive +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
