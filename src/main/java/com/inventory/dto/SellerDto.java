package com.inventory.dto;

import com.inventory.enums.Role;

public class SellerDto {

    private Long sellerId;
    private String userName;
    private String email;
    private String password;
    private Role role;

    public SellerDto() {
    }

    public SellerDto(Long sellerId, String userName, String email, String password, Role role) {
        this.sellerId = sellerId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

     public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "SellerDto{" +
                "sellerId=" + sellerId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
