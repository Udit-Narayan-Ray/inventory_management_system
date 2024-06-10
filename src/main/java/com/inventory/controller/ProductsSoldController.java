package com.inventory.controller;

import com.inventory.dto.ProductSoldDTO;
import com.inventory.dto.ProductSoldResponse;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.service.ProductsSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/ims")
public class ProductsSoldController {


    @Autowired
    private ProductsSoldService productsSoldService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(path = "/sells")
    public ResponseEntity<Object> placeOrder(@RequestBody ProductSoldDTO productSoldDTO){

        productSoldDTO.setCustomerName(productSoldDTO.getCustomerName().trim());

        if(productSoldDTO.getAdminId() == null || productSoldDTO.getAdminId() == 0){
            throw new Generic_Exception_Handling("Please Provide Valid adminId");
        }

        this.productsSoldService.placeOrder(productSoldDTO);

        return  new ResponseEntity<>(productSoldDTO,HttpStatus.CREATED);
    }



    @PreAuthorize("hasRole('SELLER')")
    @GetMapping(path = "/getSells")
    public ResponseEntity<Object> getOrders(
            @RequestParam(value = "adminId",defaultValue = "0")Long adminId,
            @RequestParam(name = "search",defaultValue = "")String search,
            @RequestParam(value = "date",defaultValue = "")String date,
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(name = "size",defaultValue = "5")int size
    ) throws ParseException {

        if(adminId == 0){
            throw new Generic_Exception_Handling("Please Provide Valid adminId");
        }
        else {
            return new ResponseEntity<>(this.productsSoldService.getSells(adminId, search, date,page, size), HttpStatus.OK);
        }

    }




}
