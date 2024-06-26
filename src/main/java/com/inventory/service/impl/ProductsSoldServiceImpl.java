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
import org.springframework.data.domain.Page;
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
    public List<ProductSoldDTO> getSells(Long adminId, String search, String date,int page, int size) throws ParseException {
        List<ProductSoldDTO> collect = null;

        List<ProductsSold> productsSoldBySeller = this.productsSoldRepo.findAllByCreatedBy(adminId);
        long count = productsSoldBySeller.stream().count();
        if((page == 0 && size == 0) && search.isBlank()) {

            System.err.println("Inside No Paging");
            if (!date.isEmpty()) {
                return this.productsSoldRepo
                        .findAllByCreatedByAndCreatedAt(adminId,new SimpleDateFormat("yyyy-MM-dd").parse(date))
                        .stream()
                        .map(productsSold -> this.modelMapper.map(productsSold, ProductSoldDTO.class))
                        .collect(Collectors.toList());
            } else {
                return productsSoldBySeller
                        .stream()
                        .map(productsSold -> this.modelMapper.map(productsSold, ProductSoldDTO.class))
                        .collect(Collectors.toList());
            }
        }
        PageRequest pageRequest =PageRequest.of(page,size, Sort.Direction.DESC,"createAt");

        if((!date.equals("") && date != null) && search.equals("")){
            try{
                Page<ProductsSold> createdByAndCreatedAt = this.productsSoldRepo
                        .findAllByCreatedByAndCreatedAt(adminId, new SimpleDateFormat("yyyy-MM-dd").parse(date), pageRequest);

                       collect  = createdByAndCreatedAt
                        .get()
                        .map(order -> {
                            ProductSoldDTO productSoldDTO = this.modelMapper.map(order, ProductSoldDTO.class);
                            productSoldDTO.setTotalBills(createdByAndCreatedAt.getTotalElements());
                            return productSoldDTO;
                        })
                        .collect(Collectors.toList());
            }catch (ParseException exception){
                throw new Generic_Exception_Handling("Given Date is Not Valid");
            }
            System.err.println("Inside Date Section : "+date);
            return collect;
        }
        if(search.equals("") && date.equals("")) {
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
        if((!search.equals("") && search != null) && date.equals(""))  {


            if(search.startsWith("OID-")){
                try {
                    Long sellId = Long.parseLong(search.substring(4));
                    Optional<ProductsSold> productsSold = this.productsSoldRepo.findById(sellId);
                    System.err.println("Inside Sells with orderId");
                    if(productsSold == null){
                        return new ArrayList<>();
                    }
                    ProductSoldDTO productSoldDTO = this.modelMapper.map(productsSold.get(), ProductSoldDTO.class);
                    productSoldDTO.setTotalBills(1);
                    return List.of(productSoldDTO);
                }catch (NoSuchElementException exception){

                    throw new Generic_Exception_Handling("No Sells Present with OrderId : "+search);

                }

            }else {

                Page<ProductsSold> productsSoldPage = this.productsSoldRepo
                        .findAllByCreatedByAndSearch(adminId, search, pageRequest);

                long totalElements = productsSoldPage.getTotalElements();
                collect = productsSoldPage
                        .get()
                        .map(order -> {
                            ProductSoldDTO productSoldDTO = this.modelMapper.map(order, ProductSoldDTO.class);
                            productSoldDTO.setTotalBills(totalElements);
                            return productSoldDTO;
                        })
                        .collect(Collectors.toList());
            }

            System.err.println("Inside Searching with paging");
            return collect;
        }

        if((!search.equals("") && !date.equals("") ) || (search != null && date != null)){

            Page<ProductsSold> searchOrCreatedAt = this.productsSoldRepo.findAllByCreatedByAndSearchAndCreatedAt(adminId, search, new SimpleDateFormat("yyyy-MM-dd").parse(date),pageRequest);
            long totalElements = searchOrCreatedAt.getTotalElements();
            System.err.println("Inside Either by Search Or Date");
            if(searchOrCreatedAt == null){
                return List.of();
            }
            return searchOrCreatedAt
                    .stream()
                    .map(searchOrCreateAt->{
                        ProductSoldDTO productSoldDTO = this.modelMapper.map(searchOrCreateAt, ProductSoldDTO.class);
                        productSoldDTO.setTotalBills(totalElements);
                        return productSoldDTO;
                    })
                    .collect(Collectors.toList());
        }
        else {
            return new ArrayList<>();
        }

    }

    @Override
    public void placeOrder(ProductSoldDTO productSoldDTO) {
        var ref = new Object() {
            HashMap<Long,Integer> productIdQuantity =new HashMap<>();
            double calculatedPrices = 0;
        };
        productSoldDTO.setPhoneNo(productSoldDTO.getPhoneNo().trim().replace(" ",""));
        productSoldDTO.setCustomerName(productSoldDTO.getCustomerName().trim());
        productSoldDTO.setDescription(productSoldDTO.getDescription().trim());

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
                                ref.productIdQuantity.put(inventory.getId(),sells.getQuantity());
                                productsSoldDetails.setInventory(inventory);
                            } catch (NoSuchElementException exception) {
                                throw new Generic_Exception_Handling("No Product Exist With ProductId :" + sells.getId());
                            }
                            if((inventory.getQuantity()-sells.getQuantity())>=0) {
                                productsSoldDetails.setQuantity(sells.getQuantity());
                            }else{
                                throw new Generic_Exception_Handling("Quantity Provided for the productId : "+sells.getId()+" is More than Current Stock");
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
