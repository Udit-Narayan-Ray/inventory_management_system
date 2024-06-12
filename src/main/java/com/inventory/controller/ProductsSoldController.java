package com.inventory.controller;

import com.inventory.dto.ProductSoldDTO;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.service.ProductsSoldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(path = "/ims")
public class ProductsSoldController {

    private Logger logger = LoggerFactory.getLogger(ProductsSoldController.class);



    @Autowired
    private ProductsSoldService productsSoldService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(path = "/sells")
    public ResponseEntity<Object> placeOrder(@RequestBody ProductSoldDTO productSoldDTO){

        productSoldDTO.setCustomerName(productSoldDTO.getCustomerName().trim());

        if(productSoldDTO.getAdminId() == null || productSoldDTO.getAdminId() == 0){
            throw new Generic_Exception_Handling("Please Provide Valid adminId");
        }

        logger.info("INSIDE ADD/UPDATE SELLS");
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
            @RequestParam(name = "size",defaultValue = "0")int size
    ) throws ParseException {
    logger.warn("INSIDE SELLS");
    if(adminId == 0){
            throw new Generic_Exception_Handling("Please Provide Valid adminId");
        }
        else {
            logger.debug("DEBUGGING");
            logger.info("Getting Sells");
            logger.trace("TRACING");
            logger.error("ERROR Logging");
            logger.warn("WARNING");

        return new ResponseEntity<>(this.productsSoldService.getSells(adminId, search, date,page, size), HttpStatus.OK);
        }

    }




}
