package com.inventory.dto;

public class Sign_Up_Dto {

    private String userName;

    private String email;

    private String password;

//    private String phoneNo;


    public Sign_Up_Dto() {
    }

    public Sign_Up_Dto(String userName, String email, String password, String phoneNo) {
        this.userName = userName;
        this.email = email;
        this.password = password;
//        this.phoneNo = phoneNo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getPhoneNo() {
//        return phoneNo;
//    }
//
//    public void setPhoneNo(String phoneNo) {
//        this.phoneNo = phoneNo;
//    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }

    @Override
    public String toString() {
        return "Sign_Up_Dto{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
//                ", phoneNo='" + phoneNo + '\'' +
//                ", roles=" + role +
                '}';
    }
}
