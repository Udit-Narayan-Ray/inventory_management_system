package com.inventory.config.security.userdetails;

import com.inventory.model.Seller;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SellerDetailsService {
    public Seller loadSellerByUserName(String username);
}
