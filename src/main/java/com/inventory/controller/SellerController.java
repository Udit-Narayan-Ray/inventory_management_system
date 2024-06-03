package com.inventory.controller;

import com.inventory.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ims")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping(path = "/getSellers")
    public ResponseEntity<Object> getSellers(@RequestParam(name = "userName", defaultValue = "",required = false) String userName){

        if(userName.equals("")){
            return ResponseEntity.ok(this.sellerService.allSellerList());
        }
        else{
            return ResponseEntity.ok(this.sellerService.findSeller(userName));
        }
    }



}
