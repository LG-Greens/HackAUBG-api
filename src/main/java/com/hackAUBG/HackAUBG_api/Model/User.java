package com.hackAUBG.HackAUBG_api.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматично генериране на уникален идентификатор
    private Long id;

    @Column(nullable = false, unique = true)  // Полето 'username' трябва да бъде уникално
    private String username;

    @Column(nullable = false)  // Паролата не трябва да бъде празна
    private String password;

    @Column(nullable = false)  // Добавяме ролята на потребителя
    private String role;
}
