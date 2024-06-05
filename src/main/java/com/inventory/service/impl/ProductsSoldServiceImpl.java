package com.inventory.service.impl;

import com.inventory.dto.ProductSoldDTO;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.model.ProductsSold;
import com.inventory.model.ProductsSoldDetails;
import com.inventory.repository.ProductsSoldDetailsRepo;
import com.inventory.repository.ProductsSoldRepo;
import com.inventory.repository.SellerRepo;
import com.inventory.service.ProductsSoldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductsSoldServiceImpl implements ProductsSoldService {

    @Autowired
    private ProductsSoldRepo productsSoldRepo;

    @Autowired
    private ProductsSoldDetailsRepo productsSoldDetailsRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductSoldDTO> getSells(Long adminId) {
        List<ProductSoldDTO> collect = this.productsSoldRepo
                .findAllByCreatedBy(adminId)
                .stream()
                .map(order -> this.modelMapper.map(order, ProductSoldDTO.class))
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public void placeOrder(ProductSoldDTO productSoldDTO) {
        System.err.println(productSoldDTO);

        ProductsSold productsSold = this.modelMapper.map(productSoldDTO, ProductsSold.class);

        productsSold.setProducts(
                                productSoldDTO.getProducts()
                                .stream()
                                .map(sells->this.modelMapper.map(sells, ProductsSoldDetails.class)).toList());

        try {
            productsSold.setCreatedBy(this.sellerRepo.findById(productSoldDTO.getAdminId()).get());
        }
        catch (NoSuchElementException exception){
            throw new Generic_Exception_Handling("No Seller Exists For sellerID"+productSoldDTO.getAdminId());
        }
        productsSold.setCreateAt(new Date());
        productsSold.setUpdateAt(new Date());
        System.out.println(productsSold);

    }


}
