package com.hackAUBG.HackAUBG_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String userName;
    private String jwtToken;
}
