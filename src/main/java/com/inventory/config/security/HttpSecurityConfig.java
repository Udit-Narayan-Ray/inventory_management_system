package com.inventory.config.security;

import com.inventory.config.token.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity

@Configuration
public class HttpSecurityConfig {

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(httpSecurityCsrfConfigurer ->
                                httpSecurityCsrfConfigurer.disable()
                )
                .cors(
                       httpSecurityCorsConfigurer ->
                               httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource())
                )


                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/ims/signin", "/ims/signup","/data").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                .httpBasic(Customizer.withDefaults())

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        ;


        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration=new CorsConfiguration();

        corsConfiguration.setAllowedHeaders(List.of("Content-Type","Authorization"));

        corsConfiguration.setAllowedMethods(List.of("GET","POST"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:8080","http://localhost:3000",
                "http://192.168.12.37:3000","http://192.168.12.56:3000","http://localhost:3001"));



        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource
                =new UrlBasedCorsConfigurationSource();

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }

}
