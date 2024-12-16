package com.hackAUBG.HackAUBG_api.Controller;

import com.hackAUBG.HackAUBG_api.DTO.AuthRequest;
import com.hackAUBG.HackAUBG_api.DTO.AuthResponse;
import com.hackAUBG.HackAUBG_api.DTO.UserRequest;
import com.hackAUBG.HackAUBG_api.Security.JwtUtil;
import com.hackAUBG.HackAUBG_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        try {
            System.err.println("Attempting to authenticate user: " + authRequest.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            System.err.println("Authentication successful for user: " + authRequest.getUsername());
            return new AuthResponse(authRequest.getUsername(), jwtUtil.generateToken(authRequest.getUsername()));
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid username or password");
        }
    }
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest authRequest) {
//        if (userService.existsByUsername(authRequest.getUsername())) {
//            throw new RuntimeException("User already exists with username: " + authRequest.getUsername());
//        }
        try {
            UserRequest userRequest = new UserRequest();
            userRequest.setUsername(authRequest.getUsername());
            userRequest.setPassword(passwordEncoder.encode(authRequest.getPassword()));
            userRequest.setRole("ANY");//TODO....

            userService.createUser(userRequest);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            return new AuthResponse(authRequest.getUsername(), jwtUtil.generateToken(authRequest.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed for username: " + authRequest.getUsername(), e);
        }
    }
}