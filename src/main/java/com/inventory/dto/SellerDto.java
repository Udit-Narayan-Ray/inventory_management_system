package com.inventory.dto;

import com.inventory.enums.Role;

public class SellerDto {

    private String userName;
    private String email;
    private String password;
    private Role role;
    private String generatedToken;

    public SellerDto() {
    }

    public SellerDto(String userName, String email, String password, String generatedToken,Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.generatedToken = generatedToken;
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

    public String getGeneratedToken() {
        return generatedToken;
    }

    public void setGeneratedToken(String generatedToken) {
        this.generatedToken = generatedToken;
    }

    @Override
    public String toString() {
        return "SellerDto{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
//                ", role=" + role +
                ", generatedToken="+generatedToken+
                '}';
    }
}
