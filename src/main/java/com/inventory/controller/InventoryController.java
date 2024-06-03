package com.inventory.controller;

import com.inventory.dto.InventoryDto;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ims")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/addProduct")
    public void addProduct(@RequestBody InventoryDto inventoryDto){

    }

    @GetMapping(path = "/getProducts")
    public ResponseEntity<Object> getProduct(@RequestParam(value = "productId", defaultValue = "") String productId){

        if(productId.equals("")){
            return new ResponseEntity<>(this.inventoryService.getAllProducts(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("",HttpStatus.OK);
        }

    }

}
