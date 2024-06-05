package com.inventory.service;

import com.inventory.dto.ProductSoldDTO;

import java.util.List;

public interface ProductsSoldService {

    public List<ProductSoldDTO> getSells(Long adminId);

    public void placeOrder(ProductSoldDTO productSoldDTO);
}
