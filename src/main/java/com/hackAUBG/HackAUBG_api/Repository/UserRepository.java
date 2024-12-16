package com.hackAUBG.HackAUBG_api.Repository;

import com.hackAUBG.HackAUBG_api.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
