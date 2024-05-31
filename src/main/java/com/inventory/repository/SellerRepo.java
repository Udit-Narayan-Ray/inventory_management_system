package com.inventory.repository;

import com.inventory.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller,Long> {

    public Seller findByUserName(String username);

}
