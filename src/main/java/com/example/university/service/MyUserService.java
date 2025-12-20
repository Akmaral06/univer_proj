package com.example.university.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username("admin")
                // пароль: admin
                .password("$2a$10$0gQs93xrC3EG79UhusGUJeBPFRRYXplMPJrSjfe4jV5U4Stw9izoi")
                .roles("ADMIN")
                .build();
    }
}
