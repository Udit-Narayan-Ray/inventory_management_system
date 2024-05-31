package com.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ims/")
public class SIGN_IN_UP_Controller {

    @PostMapping(path = "/signin")
    public ResponseEntity<String> signIn(@RequestBody String body){

        return new ResponseEntity("Welcome to Inventory "+body, HttpStatus.OK);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody String body){
        return  new ResponseEntity("User Create With Details Given"+body,HttpStatus.CREATED);
    }


}
