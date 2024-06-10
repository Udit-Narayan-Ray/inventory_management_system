package com.inventory.service.impl;

import com.inventory.dto.InventoryDto;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepo;
import com.inventory.repository.SellerRepo;
import com.inventory.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {


    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addProduct(InventoryDto inventoryDto) {

        Inventory inventory = this.modelMapper.map(inventoryDto, Inventory.class);
        inventory.setCreatedAt(new Date());
        inventory.setUpdateAt(new Date());
        inventory.setIsActive(inventoryDto.getIsActive());
        try {
            inventory.setCreatedBy(this.sellerRepo.findById(inventoryDto.getAdminId()).get());
        }
        catch (NoSuchElementException exception){
            throw new Generic_Exception_Handling("No User Found With userId : "+inventoryDto.getAdminId());
        }

        this.inventoryRepo.save(inventory);
    }

    @Override
    public void updateProduct(InventoryDto inventoryDto) {
        System.out.println(inventoryDto);
        Inventory inventory;
        try {
            inventory = this.inventoryRepo.findById(inventoryDto.getProductId()).get();
        }catch (NoSuchElementException exception){
            throw new Generic_Exception_Handling("No Product Exists With productId : "+inventoryDto.getProductId());
        }
        if(inventoryDto.getQuantity() != 0 ){
            inventory.setQuantity(inventoryDto.getQuantity());
        }
        if(inventoryDto.getIsActive() == true){
            System.err.println("INTO TRUE "+inventoryDto.getIsActive());
            inventory.setIsActive(true);
        }else {
            System.err.println("INTO FALSE "+inventoryDto.getIsActive());
            inventory.setIsActive(false);
        }
        if((inventoryDto.getProductName()!=null || !inventoryDto.getProductName().equals("")) && !inventoryDto.getProductName().equals(inventory.getProductName())){
            inventory.setProductName(inventoryDto.getProductName());
        }
        if((inventoryDto.getProductType()!=null || !inventoryDto.getProductType().equals(""))&& !inventoryDto.getProductType().equals(inventory.getProductType())){
            inventory.setProductType(inventoryDto.getProductType());
        }
        if(inventoryDto.getMinQuantity()!= 0 && inventoryDto.getMinQuantity()!=inventory.getMinQuantity()){
            inventory.setMinQuantity(inventoryDto.getMinQuantity());
        }

        if(inventoryDto.getSellingPrice()!=0 && inventoryDto.getSellingPrice() != inventory.getSellingPrice()){
            inventory.setSellingPrice(inventoryDto.getSellingPrice());
        }

        if(inventoryDto.getCostPrice()!=0 && inventoryDto.getCostPrice()!=inventory.getCostPrice()){
            inventory.setCostPrice(inventoryDto.getCostPrice());
        }

        inventory.setUpdateAt(new Date());


        this.inventoryRepo.save(inventory);

    }

    @Override
    public InventoryDto getProducts(Long productId) {

        Inventory inventory = this.inventoryRepo.findById(productId).orElse(null);
        if(inventory == null){
            throw new Generic_Exception_Handling("No Product Exist With productId : "+productId);
        }
        return this.modelMapper.map(inventory, InventoryDto.class);
    }


    @Override
    public List<InventoryDto> getAllProducts(Long sellerId) {


        List<InventoryDto> collect = this.inventoryRepo
                .findAllByCreatedBy(sellerId)
                .stream()
                .map(product -> this.modelMapper.map(product, InventoryDto.class))
                .collect(Collectors.toList());

        return collect;

    }


}
