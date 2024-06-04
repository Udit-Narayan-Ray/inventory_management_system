package com.inventory.controller;

import com.inventory.dto.InventoryDto;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ims")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(path = "/addProduct")
    public ResponseEntity<Object> addProduct(@RequestBody InventoryDto inventoryDto){
        if(inventoryDto.getProductId() == null || inventoryDto.getProductId() == 0)
        {
            this.inventoryService.addProduct(inventoryDto);
        }
        else {
            this.inventoryService.updateProduct(inventoryDto);
        }
      return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping(path = "/getProducts")
    public ResponseEntity<Object> getProduct(@RequestParam(value = "productId", defaultValue = "") Long productId){

        if(productId == null || productId == 0){
            return new ResponseEntity<>(this.inventoryService.getAllProducts(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(this.inventoryService.getProducts(productId),HttpStatus.OK);
        }
    }

}
