package com.inventory.service;

import com.inventory.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    public void addProduct(InventoryDto inventoryDto);

    public void updateProduct(InventoryDto inventoryDto);

    public InventoryDto getProducts(Long productId);

    public List<InventoryDto> getAllProducts(Long sellerId);
}
