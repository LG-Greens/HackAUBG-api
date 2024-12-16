package com.hackAUBG.HackAUBG_api.Controller;

import com.hackAUBG.HackAUBG_api.DTO.AuthResponse;
import com.hackAUBG.HackAUBG_api.Security.JwtUtil;
import com.hackAUBG.HackAUBG_api.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            return new AuthResponse(loginRequest.getUsername(), jwtUtil.generateToken(loginRequest.getUsername()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password");
        }
    }

}