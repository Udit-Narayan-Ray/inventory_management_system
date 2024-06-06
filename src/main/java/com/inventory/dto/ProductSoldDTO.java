package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductSoldDTO {


        private Long adminId;

        private String transactionId;

        @JsonIgnore
        private Long sellId;

        private String customerName;

        private String phoneNo;

        private double totalAmount;

        @JsonIgnore
        private double totalCost;

        private String description;

        private String date;

        @JsonIgnore
        private Date createAt;


        private List<ProductSoldDetailsDTO> products;

        public ProductSoldDTO() {
        }

        public ProductSoldDTO(Long adminId, String orderId, Long sellId, String customerName,
                              String phoneNo, double totalCost, String description, List<ProductSoldDetailsDTO> products) {
                this.adminId = adminId;
                this.transactionId = orderId;
                this.sellId = sellId;
                this.customerName = customerName;
                this.phoneNo = phoneNo;
            this.totalCost = totalCost;
            this.description = description;
                this.products = products;
        }

        public Long getAdminId() {
                return adminId;
        }

        public void setAdminId(Long adminId) {
                this.adminId = adminId;
        }

        public String getTransactionId() {
                return "OID-"+this.sellId;
        }

        public void setTransactionId(String transactionId) {
                this.transactionId = transactionId;
        }

        public Long getSellId() {
                return sellId;
        }

        public void setSellId(Long sellId) {
                this.sellId = sellId;
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

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public List<ProductSoldDetailsDTO> getProducts() {
                return products;
        }

        public void setProducts(List<ProductSoldDetailsDTO> products) {
                this.products = products;
        }

        @Override
        public String toString() {
                return "ProductSoldDTO{" +
                        "adminId=" + adminId +
                        ", transactionId='" + transactionId + '\'' +
                        ", sellId=" + sellId +
                        ", customerName='" + customerName + '\'' +
                        ", phoneNo='" + phoneNo + '\'' +
                        ", description='" + description + '\'' +
                        ", products=" + products +
                        '}';
        }

        public double getTotalCost() {
                if(totalCost == 0){
                        return totalAmount;
                }
                return totalCost;
        }

        public void setTotalCost(double totalCost) {
                this.totalCost = totalCost;
        }

        public double getTotalAmount() {
                if(totalAmount == 0){
                        return this.totalCost;
                }
                return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
        }

        public String getDate() {
                if(createAt != null){
                        return new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(this.createAt);
                }
                else return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public void setCreateAt(Date createAt) {
                this.createAt = createAt;
        }

        public Date getCreateAt() {
                return createAt;
        }
}
