package com.hackAUBG.HackAUBG_api.Service;

import com.hackAUBG.HackAUBG_api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("Attempting to load user with username: " + username);

        com.hackAUBG.HackAUBG_api.Model.User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        System.err.println("User found: " + userEntity.getUsername());
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword()) // Криптирана парола
                .roles(userEntity.getRole())
                .build();
    }
}
