package com.hackAUBG.HackAUBG_api.Controller;


import com.hackAUBG.HackAUBG_api.DTO.UserRequest;
import com.hackAUBG.HackAUBG_api.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }
}