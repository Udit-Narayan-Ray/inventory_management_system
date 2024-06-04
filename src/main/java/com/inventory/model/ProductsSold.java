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
    private List<ProductsSoldDetails> productsSoldDetails;

    private double totalCost;

    private String customerName;

    @Column(nullable = false)
    private String customerPhoneNo;


    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private Seller createdBy;

    @Column(nullable = false)
    private Date createAt;

    private Date updateAt;

    public ProductsSold() {
    }

    public ProductsSold(Long sellId, List<ProductsSoldDetails> productsSoldDetails, double totalCost,
                        String customerName, String customerPhoneNo, Seller createdBy, Date createAt, Date updateAt) {
        this.sellId = sellId;
        this.productsSoldDetails = productsSoldDetails;
        this.totalCost = totalCost;
        this.customerName = customerName;
        this.customerPhoneNo = customerPhoneNo;
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

    public List<ProductsSoldDetails> getProductsSoldDetails() {
        return productsSoldDetails;
    }

    public void setProductsSoldDetails(List<ProductsSoldDetails> productsSoldDetails) {
        this.productsSoldDetails = productsSoldDetails;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
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

    @Override
    public String toString() {
        return "ProductsSold{" +
                "sellId=" + sellId +
                ", productsSoldDetails=" + productsSoldDetails +
                ", totalCost=" + totalCost +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNo='" + customerPhoneNo + '\'' +
                ", createdBy=" + createdBy +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
