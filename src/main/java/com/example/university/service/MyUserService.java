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
                .password("$2a$10$7QJm9Qy0Hq0zX6vGQ8dGXeZQm1jP7u8cY6Z0k8wE1G1lZ3pJq")
                .roles("ADMIN")
                .build();
    }
}
