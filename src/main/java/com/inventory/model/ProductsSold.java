package com.inventory.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class ProductsSold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellId;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id")
    private List<ProductsSoldDetails> products;

    private double totalCost;

    private String customerName;

    @Column(nullable = false)
    private String phoneNo;

    private String description;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private Seller createdBy;

    @Column(nullable = false)
    private Date createAt;

    private Date updateAt;

    public ProductsSold() {
    }

    public ProductsSold(Long sellId, List<ProductsSoldDetails> products, double totalCost,
                        String customerName, String phoneNo, String description, Seller createdBy, Date createAt, Date updateAt) {
        this.sellId = sellId;
        this.products = products;
        this.totalCost = totalCost;
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.description = description;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getSellId() {
        return sellId;
    }

    public void setSellId(Long sellId) {
        this.sellId = sellId;
    }

    public List<ProductsSoldDetails> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsSoldDetails> products) {
        this.products = products;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Seller getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Seller createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }


    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductsSold{" +
                "sellId=" + sellId +
                ", products=" + products +
                ", totalCost=" + totalCost +
                ", customerName='" + customerName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

}
