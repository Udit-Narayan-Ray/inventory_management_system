package com.inventory.controller;

import com.inventory.dto.SellerDto;
import com.inventory.dto.Sign_Up_Dto;
import com.inventory.dto.TokenDto;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.service.JwtService;
import com.inventory.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ims/")
public class SIGN_IN_UP_Controller {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SellerService sellerService;

    @PostMapping(path = "/signin")
    public ResponseEntity<String> signIn(@RequestBody SellerDto sellerDto){

        SellerDto sellerDto1 = this.sellerService.findSeller(sellerDto.getEmail());
        if(!this.passwordEncoder.matches(sellerDto.getPassword(), sellerDto1.getPassword())){
            throw new Generic_Exception_Handling("Bad credentials ! Check Password");
        }
        sellerDto.setGeneratedToken(this.jwtService.generateToken(sellerDto.getEmail()));

        return new ResponseEntity(new TokenDto(sellerDto1.getUserName(),sellerDto.getEmail(),sellerDto.getGeneratedToken()), HttpStatus.OK);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody Sign_Up_Dto signUpDto){
        this.sellerService.createSeller(signUpDto);
        return  new ResponseEntity(signUpDto,HttpStatus.CREATED);
    }


}
