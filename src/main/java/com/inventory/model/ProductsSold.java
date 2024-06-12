package com.inventory.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class ProductsSold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sold_id")
    private List<ProductsSoldDetails> products;

    private double totalCost;

    @Column(length = 50)
    private String customerName;

    @Column(nullable = false,length = 13)
    private String phoneNo;

    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private Seller createdBy;

    @Column(nullable = false)
    private Date createAt;

    private Date updateAt;

    public ProductsSold() {
    }

    public ProductsSold(Long id, List<ProductsSoldDetails> products, double totalCost,
                        String customerName, String phoneNo, String description, Seller createdBy, Date createAt, Date updateAt) {
        this.id = id;
        this.products = products;
        this.totalCost = totalCost;
        this.customerName = customerName;
        this.phoneNo = phoneNo;
        this.description = description;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
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
