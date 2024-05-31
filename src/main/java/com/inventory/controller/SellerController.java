package com.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ims")
public class SellerController {


    @PreAuthorize("hasRole('SELLER')")
    @GetMapping(path = "/getSellers")
    public ResponseEntity<String> getSellers(){

        return ResponseEntity.ok("Seller Lists");
    }

}
