package com.example.test_spring.bussiness;

import java.util.Objects;

import com.example.test_spring.entity.User;
import com.example.test_spring.service.UserService;
import org.springframework.stereotype.Service;

import com.example.test_spring.exception.BaseException;
import com.example.test_spring.exception.UserException;
import com.example.test_spring.model.MRegisterRequest;

@Service
public class UserBusiness {
    private final UserService userService;

    public UserBusiness(UserService userService) {
        this.userService = userService;
    }

    public User register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(),request.getPassword(),request.getName());
        return user;

    }
}
