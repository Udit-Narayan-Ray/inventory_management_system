package com.inventory.config.security;

import com.inventory.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AuthenticationConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    UserDetailsService userDetailsService(){

        UserDetails seller = User.builder()
                .username("rku")
                .password(passwordEncoder.encode("rku"))
                .roles("SELLER")
                .build();

        UserDetails user = User
                .withUsername("udit")
                .password(passwordEncoder.encode("udit"))
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(seller,user);
    }


}
