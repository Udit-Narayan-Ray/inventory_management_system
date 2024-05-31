package com.inventory.config.security.userdetails;

import org.springframework.security.core.GrantedAuthority;

public interface SellerDetails {

    String getUserName();
    String getPassword();
    GrantedAuthority getAuthorities();
    boolean isActive();
}
