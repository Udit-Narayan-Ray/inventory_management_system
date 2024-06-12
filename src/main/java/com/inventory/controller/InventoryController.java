package com.inventory.controller;

import com.inventory.dto.InventoryDto;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.service.InventoryService;
import com.inventory.service.SellerService;
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

    @Autowired
    private SellerService sellerService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(path = "/addOrUpdateProduct")
    public ResponseEntity<Object> addProduct(@RequestBody InventoryDto inventoryDto){
        HttpStatus status;
        if(inventoryDto.getProductId() == null || inventoryDto.getProductId() == 0)
        {
            status = HttpStatus.CREATED;
            this.inventoryService.addProduct(inventoryDto);
        }
        else {
            this.inventoryService.updateProduct(inventoryDto);
            status = HttpStatus.OK;
        }
      return new ResponseEntity<>(inventoryDto,status);
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping(path = "/getProducts")
    public ResponseEntity<Object> getProduct(@RequestParam(value = "productId", defaultValue = "0") Long productId,@RequestParam(value = "adminId",defaultValue = "0")Long adminId){

        if(productId == null || productId == 0 ){
            if(adminId == 0 || adminId == null){
                throw new Generic_Exception_Handling("No Admin ID Sent, Please Sent IT in next Request");
            }
            this.sellerService.findSellerById(adminId);
            return new ResponseEntity<>(this.inventoryService.getAllProducts(adminId), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(this.inventoryService.getProducts(productId),HttpStatus.OK);
        }
    }

}
