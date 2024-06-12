package com.inventory.model;

import jakarta.persistence.*;

@Entity
public class ProductsSoldDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;


    public ProductsSoldDetails() {
    }

    public ProductsSoldDetails(Long slNo, int quantity, Inventory inventory) {
        this.id = slNo;
        this.quantity = quantity;
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", quantity=" + quantity +
                ", inventory=" + inventory +
                '}';
    }
}
