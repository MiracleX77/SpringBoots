package com.example.test_spring.service;

import com.example.test_spring.entity.User;
import com.example.test_spring.exception.BaseException;
import com.example.test_spring.exception.UserException;
import com.example.test_spring.repository.UserRepository;
import com.example.test_spring.config.SecurityConfig.*;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository repository;


    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    public User create(String email,String password,String name) throws BaseException {

        if(Objects.isNull(name)){
            throw UserException.createNameNull();
        }
        if(Objects.isNull(email)){
            throw UserException.createEmailNull();
        }
        if(Objects.isNull(password)){
            throw UserException.createPasswordNull();
        }
        if(repository.existsByEmail(email)){
            throw UserException.verifyEmail();
        }


        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);
        return repository.save(entity);
    }
}
