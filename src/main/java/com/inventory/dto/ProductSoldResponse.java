package com.inventory.dto;

import java.util.List;

public class ProductSoldResponse {

    private Long totalBills;
    private List<ProductSoldDTO> sells;

    public ProductSoldResponse() {
    }

    public ProductSoldResponse(Long totalBills, List<ProductSoldDTO> sells) {
        this.totalBills = totalBills;
        this.sells = sells;
    }


    public Long getTotalBills() {
        return totalBills;
    }

    public void setTotalBills(Long totalBills) {
        this.totalBills = totalBills;
    }

    public List<ProductSoldDTO> getSells() {
        return sells;
    }

    public void setSells(List<ProductSoldDTO> sells) {
        this.sells = sells;
    }

    @Override
    public String toString() {
        return "ProductSoldResponse{" +
                "totalBills=" + totalBills +
                ", sells=" + sells +
                '}';
    }
}
