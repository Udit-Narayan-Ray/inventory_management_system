package com.inventory.service.impl;

import com.inventory.dto.InventoryDto;
import com.inventory.repository.InventoryRepo;
import com.inventory.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements InventoryService {


    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveProduct(InventoryDto inventoryDto) {

    }

    @Override
    public List<InventoryDto> getAllProducts() {

        return  this.inventoryRepo
                .findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, InventoryDto.class))
                .collect(Collectors.toList());
    }


}
