package com.inventory.service.impl;

import com.inventory.dto.ProductSoldDTO;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.model.Inventory;
import com.inventory.model.ProductsSold;
import com.inventory.model.ProductsSoldDetails;
import com.inventory.repository.InventoryRepo;
import com.inventory.repository.ProductsSoldDetailsRepo;
import com.inventory.repository.ProductsSoldRepo;
import com.inventory.repository.SellerRepo;
import com.inventory.service.ProductsSoldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductsSoldServiceImpl implements ProductsSoldService {

    @Autowired
    private ProductsSoldRepo productsSoldRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ProductsSoldDetailsRepo productsSoldDetailsRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductSoldDTO> getSells(Long adminId, String customerName, String date,int page, int size)  {
        List<ProductSoldDTO> collect = null;

        PageRequest pageRequest =PageRequest.of(page,size, Sort.Direction.ASC,"createAt");
        long count = this.productsSoldRepo.findAllByCreatedBy(adminId).stream().count();
        if(!date.equals("")){
            try{
                collect = this.productsSoldRepo
                        .findAllByCreatedByAndCreatedAt(adminId,new SimpleDateFormat("yyyy-MM-dd").parse(date))
                        .stream()
                        .map(order -> this.modelMapper.map(order, ProductSoldDTO.class))
                        .collect(Collectors.toList());
            }catch (ParseException exception){
                throw new Generic_Exception_Handling("Given Date is Not Valid");
            }
            System.err.println("Inside Date Section : "+date);
            return collect;
        }

        
        if(customerName.equals("") && date.equals("")) {
             collect = this.productsSoldRepo
                    .findAllByCreatedBy(adminId, pageRequest)
                    .get()
                    .map(order -> {
                        ProductSoldDTO productSoldDTO = this.modelMapper.map(order, ProductSoldDTO.class);
                        productSoldDTO.setTotalBills(count);
                        return productSoldDTO;
                    })
                    .collect(Collectors.toList());
            System.err.println("Inside Sells with Paging");
            return collect;
        }
        if(!customerName.equals("") && customerName != null) {
            collect = this.productsSoldRepo
                    .findAllByCreatedByAndCustomerNameContaining(adminId,customerName, pageRequest)
                    .get()
                    .map(order -> {
                        ProductSoldDTO productSoldDTO = this.modelMapper.map(order, ProductSoldDTO.class);
                        productSoldDTO.setTotalBills(count);
                        return productSoldDTO;
                    })
                    .collect(Collectors.toList());
            System.err.println("Inside Customer Name Finding with paging");
            return collect;
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public void placeOrder(ProductSoldDTO productSoldDTO) {
        var ref = new Object() {
            HashMap<Long,Integer> productIdQuantity =new HashMap<>();
            double calculatedPrices = 0;
        };
        ProductsSold productsSold = this.modelMapper.map(productSoldDTO, ProductsSold.class);


        productsSold.setProducts(
                productSoldDTO
                .getProducts()
                .stream()
                .map(
                        sells -> {
                            ProductsSoldDetails productsSoldDetails = new ProductsSoldDetails();
                            Inventory inventory;
                            try {
                                inventory = this.inventoryRepo.findById(sells.getProductId()).get();
                                ref.calculatedPrices += inventory.getSellingPrice()*sells.getQuantity();
                                ref.productIdQuantity.put(inventory.getProductId(),sells.getQuantity());
                                productsSoldDetails.setInventory(inventory);
                            } catch (NoSuchElementException exception) {
                                throw new Generic_Exception_Handling("No Product Exist With ProductId :" + sells.getProductId());
                            }
                            if((inventory.getQuantity()-sells.getQuantity())>=0) {
                                productsSoldDetails.setQuantity(sells.getQuantity());
                            }else{
                                throw new Generic_Exception_Handling("Quantity Provided for the productId : "+sells.getProductId()+" is More than Current Stock");
                            }
                            return productsSoldDetails;
                        })
                .collect(Collectors.toList()));

        try {
            productsSold.setCreatedBy(this.sellerRepo.findById(productSoldDTO.getAdminId()).get());
        }
        catch (NoSuchElementException exception){
            throw new Generic_Exception_Handling("No Seller Exists For sellerID"+productSoldDTO.getAdminId());
        }
        productsSold.setCreateAt(new Date());
        productsSold.setUpdateAt(new Date());
        if(productSoldDTO.getTotalAmount() == ref.calculatedPrices){
            this.productsSoldRepo.save(productsSold);
           for(Map.Entry entry:ref.productIdQuantity.entrySet() ){
                            Inventory inventory = this.inventoryRepo.findById((Long) entry.getKey()).get();
                            inventory.setQuantity(inventory.getQuantity()-(int)entry.getValue());
                            inventory.setUpdateAt(new Date());
                            this.inventoryRepo.save(inventory);
                        }
        }else {
            throw new Generic_Exception_Handling("Calculated Amount For Sells is Wrong, Please Recheck It");
        }

    }


}
