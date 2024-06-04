package com.inventory.dto;

public class TokenDto {

    private Long sellerId;
    private String userName;
    private String email;
    private String jwtToken;

    public TokenDto() {
    }

    public TokenDto(Long sellerId, String userName, String email, String jwtToken) {
        this.sellerId = sellerId;
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


    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "sellerId='" + sellerId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
