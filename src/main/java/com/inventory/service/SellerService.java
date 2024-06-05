package com.inventory.service;

import com.inventory.dto.SellerDto;
import com.inventory.dto.Sign_Up_Dto;

import java.util.List;

public interface SellerService {

    public SellerDto findSeller(String userName);
    public SellerDto findSellerById(Long sellerId);
    public void createSeller(Sign_Up_Dto signUpDto);
    public List<SellerDto> allSellerList();

}
