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


    public ProductsSoldDetails() {
    }

    public ProductsSoldDetails(Long slNo, int quantity, Inventory inventory) {
        this.slNo = slNo;
        this.quantity = quantity;
        this.inventory = inventory;
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



    @Override
    public String toString() {
        return "ProductsSoldDetails{" +
                "slNo=" + slNo +
                ", quantity=" + quantity +
                ", inventory=" + inventory +
                '}';
    }
}
