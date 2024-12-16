package com.hackAUBG.HackAUBG_api.Service;


import com.hackAUBG.HackAUBG_api.DTO.UserRequest;
import com.hackAUBG.HackAUBG_api.Model.User;
import com.hackAUBG.HackAUBG_api.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void createUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    public boolean existsByUsername(String userName){
        return userRepository.findByUsername(userName).isPresent();
    }
}
