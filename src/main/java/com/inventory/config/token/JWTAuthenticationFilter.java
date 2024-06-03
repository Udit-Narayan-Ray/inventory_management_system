package com.inventory.config.token;

import com.inventory.dto.CustomSellerDetails;
import com.inventory.exceptions.Generic_Exception_Handling;
import com.inventory.repository.SellerRepo;
import com.inventory.service.JwtService;
import com.inventory.service.SellerService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private String email = null;
    private String jwtToken = null;
    private CustomSellerDetails sellerDetails = null;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String validToken = null;
        jwtToken = request.getHeader("Authorization");

        if(jwtToken != null && jwtToken.startsWith("Bearer ")){
            validToken = jwtToken.substring(7);
            try {
                if(this.jwtService.validateToken(validToken)){
                    email = this.jwtService.getUsernameFromToken(validToken);
                }
            }
            catch (ExpiredJwtException expiredJwtException){
                throw new Generic_Exception_Handling("Token has Expired Please Generate by Signing In");
            }
            catch (Exception exception){
                throw new Generic_Exception_Handling("! Token Related Errors !");
            }
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            sellerDetails = this.modelMapper.map(this.sellerService.findSeller(email), CustomSellerDetails.class);

                UsernamePasswordAuthenticationToken authenticationToken = new
                        UsernamePasswordAuthenticationToken(sellerDetails,null,sellerDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }

        filterChain.doFilter(request,response);
    }
}
