package com.inventory.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    //retrieve username from jwt token
    String getUsernameFromToken(String token);

    //generate token for user
    String generateToken(String username);

    //validate token
    Boolean validateToken(String token);

}
