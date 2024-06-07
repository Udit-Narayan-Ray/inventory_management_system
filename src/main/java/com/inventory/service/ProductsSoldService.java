package com.inventory.service;

import com.inventory.dto.ProductSoldDTO;

import java.text.ParseException;
import java.util.List;

public interface ProductsSoldService {

    public List<ProductSoldDTO> getSells(Long adminId, String customerName, String date, int page, int size) throws ParseException;

    public void placeOrder(ProductSoldDTO productSoldDTO);
}
