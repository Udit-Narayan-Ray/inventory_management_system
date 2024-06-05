package com.inventory.controller;

import com.inventory.dto.ProductSoldDTO;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.service.ProductsSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ims")
public class ProductsSoldController {


    @Autowired
    private ProductsSoldService productsSoldService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(path = "/sells")
    public ResponseEntity<Object> placeOrder(@RequestBody ProductSoldDTO productSoldDTO, @RequestParam(value = "adminId",defaultValue = "0")Long adminId){

        if(adminId == 0){
            throw new Generic_Exception_Handling("Please Provide Valid adminId");
        }

        this.productsSoldService.placeOrder(productSoldDTO);

        return  new ResponseEntity<>(productSoldDTO,HttpStatus.CREATED);
    }



    @PreAuthorize("hasRole('SELLER')")
    @GetMapping(path = "/getSells")
    public ResponseEntity<Object> getOrders(@RequestParam(value = "adminId",defaultValue = "0")Long adminId ){

        if(adminId == 0){
            throw new Generic_Exception_Handling("Please Provide Valid adminId");
        }
        else {
            return new ResponseEntity<>(this.productsSoldService.getSells(adminId), HttpStatus.OK);
        }

    }




}
