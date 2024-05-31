package com.inventory.model;

import com.inventory.enums.Roles;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Column(nullable = false)
    private String sellerName;

    @Column(unique = true,nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNo;

    @Column(nullable = false)
    private Roles roles;


    @Column(nullable = false)
    private Date createdAt;

    private Date updateAt;

    public Seller() {
    }

    public Seller(Long sellerId, String sellerName, String userName, String password,
                  String phoneNo, Roles roles, Date createdAt, Date updateAt) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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
        return "Seller{" +
                "sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", roles=" + roles +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
