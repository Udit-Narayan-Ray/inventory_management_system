package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TokenDto {

    private Long adminId;
    @JsonIgnore
    private Long id;
    private String userName;
    private String email;
    private String jwtToken;

    public TokenDto() {
    }

    public TokenDto(Long id, String userName, String email, String jwtToken) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }

    public Long getAdminId() {
        return this.id;
    }

    public void setAdminId(Long adminId) {
        this.adminId = id;
    }
}
