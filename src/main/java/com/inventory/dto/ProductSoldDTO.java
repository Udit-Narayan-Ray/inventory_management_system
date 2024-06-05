package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ProductSoldDTO {


        private Long adminId;

        private String orderId;

        @JsonIgnore
        private Long sellId;

        private String customerName;

        private String phoneNo;

        private double totalAmount;

        @JsonIgnore
        private double totalCost;

        private String description;

        private List<ProductSoldDetailsDTO> products;

        public ProductSoldDTO() {
        }

        public ProductSoldDTO(Long adminId, String orderId, Long sellId, String customerName,
                              String phoneNo, double totalCost, String description, List<ProductSoldDetailsDTO> products) {
                this.adminId = adminId;
                this.orderId = orderId;
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

        public String getOrderId() {
                return "OID-"+this.sellId;
        }

        public void setOrderId(String orderId) {
                this.orderId = orderId;
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
                        ", orderId='" + orderId + '\'' +
                        ", sellId=" + sellId +
                        ", customerName='" + customerName + '\'' +
                        ", phoneNo='" + phoneNo + '\'' +
                        ", description='" + description + '\'' +
                        ", products=" + products +
                        '}';
        }

        public double getTotalCost() {
                return totalAmount;
        }

        public void setTotalCost(double totalCost) {
                this.totalCost = totalAmount;
        }

        public double getTotalAmount() {
                return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
        }
}
