package com.inventory.service;

import com.inventory.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    public void saveProduct(InventoryDto inventoryDto);

    public List<InventoryDto> getAllProducts();
}
