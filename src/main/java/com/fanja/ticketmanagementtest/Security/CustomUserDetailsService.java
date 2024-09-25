package com.fanja.ticketmanagementtest.Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {

        return User.builder()
                .username(username)
                .password("{bcrypt}$2a$10$...")
                .roles("USER")
                .build();
    }
}
