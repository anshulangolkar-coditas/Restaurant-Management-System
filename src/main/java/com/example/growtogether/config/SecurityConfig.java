package com.example.growtogether.config;

import com.example.growtogether.filter.JwtFilter;
import com.example.growtogether.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
//@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/auth/login","/auth/register","/auth/refresh").permitAll()
                        .requestMatchers("/branch/**").authenticated()
                        .requestMatchers("/auth/**").authenticated()
                        .requestMatchers("/invitation/**").authenticated()
                        .requestMatchers("/restaurant/**").authenticated()
                        .requestMatchers("/branch/table/**").authenticated()
                        .requestMatchers("/restaurant/branch/staff/**").authenticated()
                        .anyRequest().permitAll()
                );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

/*    requestMatchers("/growtogether/v1/auth/refresh").hasAnyRole("ADMIN","OWNER","MANAGER", "STAFF_WAITER","STAFF_KITCHEN")
                        .requestMatchers("/growtogether/v1/auth/logout").hasAnyRole("ADMIN","OWNER","MANAGER", "STAFF_WAITER","STAFF_KITCHEN")
                        .requestMatchers(HttpMethod.POST,"/growtogether/v1/branch/").hasAnyRole("ADMIN","OWNER","MANAGER")
                        .requestMatchers(HttpMethod.GET,"/growtogether/v1/branch/").hasAnyRole("ADMIN","OWNER")
                        .requestMatchers("/growtogether/v1/branch/update-staff-salary").hasAnyRole("ADMIN","OWNER","MANAGER")
                        .requestMatchers("/growtogether/v1/branch/update-manager-salary").hasAnyRole("ADMIN","OWNER")
                        .requestMatchers(HttpMethod.DELETE,"/growtogether/v1/branch/**").hasAnyRole("ADMIN","OWNER")
                        .requestMatchers("/growtogether/v1/invitation/**").hasAnyRole("ADMIN","OWNER","MANAGER")*/


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);

    }



}
