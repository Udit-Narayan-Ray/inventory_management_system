package com.inventory.service.impl;

import com.inventory.dto.SellerDto;
import com.inventory.dto.Sign_Up_Dto;
import com.inventory.enums.Role;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.model.Seller;
import com.inventory.repository.SellerRepo;
import com.inventory.service.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SellerDto findSeller(String userName) {
        Seller seller = this.sellerRepo.findByEmail(userName);

        if(seller == null){
            throw new Generic_Exception_Handling("Seller "+userName+" Not Exists, Please Sign Up");
        }else {
            return this.modelMapper.map(seller, SellerDto.class);
        }
    }

    @Override
    public void createSeller(Sign_Up_Dto signUpDto) {

        if(this.sellerRepo.findByEmail(signUpDto.getEmail()) != null){
            throw new Generic_Exception_Handling("Seller "+signUpDto.getEmail()+" Already Exist, Please Sign In");
        }
        else {
            Seller seller = this.modelMapper.map(signUpDto, Seller.class);
            seller.setPassword(this.passwordEncoder.encode(seller.getPassword()));
            seller.setActive(true);
            seller.setRole(Role.ROLE_SELLER);
//            seller.setPhoneNo("");
            seller.setCreatedAt(new Date());
            seller.setUpdateAt(new Date());

            this.sellerRepo.save(seller);
        }
    }

    @Override
    public List<SellerDto> allSellerList() {

        return this.sellerRepo.findAll()
                .stream()
                .map(seller -> this.modelMapper.map(seller, SellerDto.class))
                .collect(Collectors.toList());
    }
}
