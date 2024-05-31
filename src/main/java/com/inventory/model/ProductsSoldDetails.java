package com.inventory.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ProductsSoldDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slNo;

    @Column(nullable = false)
    private int quantity;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Inventory inventory;

    @Column(nullable = false)
    private Date createdAt;

    private Date updatedAt;

    public ProductsSoldDetails() {
    }

    public ProductsSoldDetails(Long slNo, int quantity, Inventory inventory, Date createdAt, Date updatedAt) {
        this.slNo = slNo;
        this.quantity = quantity;
        this.inventory = inventory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getSlNo() {
        return slNo;
    }

    public void setSlNo(Long slNo) {
        this.slNo = slNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "ProductsSoldDetails{" +
                "slNo=" + slNo +
                ", quantity=" + quantity +
                ", inventory=" + inventory +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
