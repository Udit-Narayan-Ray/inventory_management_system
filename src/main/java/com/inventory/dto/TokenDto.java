package com.inventory.dto;

public class TokenDto {
    private String userName;
    private String email;
    private String generatedToken;

    public TokenDto() {
    }

    public TokenDto(String userName, String email, String generatedToken) {
        this.userName = userName;
        this.email = email;
        this.generatedToken = generatedToken;
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

    public String getGeneratedToken() {
        return generatedToken;
    }

    public void setGeneratedToken(String generatedToken) {
        this.generatedToken = generatedToken;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", generatedToken='" + generatedToken + '\'' +
                '}';
    }
}
