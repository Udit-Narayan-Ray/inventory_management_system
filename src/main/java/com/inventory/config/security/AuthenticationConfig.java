package com.inventory.config.security;

import com.inventory.config.security.userdetails.SellerDetailsService;
import com.inventory.config.security.userdetails.impl.SellerDetailsServiceImpl;
import com.inventory.enums.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AuthenticationConfig {


    @Bean
    UserDetailsService userDetailsService(){

        UserDetails seller = User.builder()
                .username("rku")
                .password(passwordEncoder().encode("rku"))
                .roles(""+Roles.SELLER)
                .build();

        UserDetails user = User
                .withUsername("udit")
                .password(passwordEncoder().encode("udit"))
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(seller,user);
    }

    @Bean
    public SellerDetailsService sellerDetailsService(){
        return new SellerDetailsServiceImpl();
    }


    @Bean
    public  AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    public


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
