package com.hackAUBG.HackAUBG_api.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"admin".equals(username)) { // Ensure "admin" is the username
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username("admin")
                .password("$2a$10$rkT5pbbXfyup4jFpAD7Q2.NAAXX1cyTDZkPJ2WB3B6q097Fuoad2C") // BCrypt hash of "password"
                .roles("USER")
                .build();
    }
}
