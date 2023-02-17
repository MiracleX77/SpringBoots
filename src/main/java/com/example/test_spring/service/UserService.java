package com.example.test_spring.service;

import com.example.test_spring.entity.User;
import com.example.test_spring.exception.BaseException;
import com.example.test_spring.exception.UserException;
import com.example.test_spring.repository.UserRepository;


import org.mapstruct.ap.internal.model.common.PresenceCheck;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean matchPassword(String rawPassword,String encodedPassword){
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }
    public Optional<User> findByEmail(String email){

        return repository.findByEmail(email);
    }

    public User updateName(String id,String name) throws BaseException {
        Optional<User> opt = repository.findById(id);
        if(opt.isEmpty()){
            throw  UserException.notFound();
        }
        User user = opt.get();
        user.setName(name);
        return repository.save(user);
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
    public void deleteById(String id){
        repository.deleteById(id);
    }
}
