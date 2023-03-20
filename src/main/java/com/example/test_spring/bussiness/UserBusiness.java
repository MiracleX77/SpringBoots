package com.example.test_spring.bussiness;

import com.example.test_spring.entity.User;
import com.example.test_spring.exception.UserException;
import com.example.test_spring.mapper.UserMapper;
import com.example.test_spring.model.MLoginRequest;
import com.example.test_spring.model.MRegisterRequest;
import com.example.test_spring.model.MRegisterResponse;
import com.example.test_spring.service.TokenService;
import com.example.test_spring.service.UserService;
import com.example.test_spring.utill.SecurityUtill;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.test_spring.exception.BaseException;

import java.util.Optional;

@Service
public class UserBusiness {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }
    public String login(MLoginRequest request) throws BaseException{
        //validate request
        System.out.println(request.toString());
        //verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw UserException.loginEmailNotFound();
        }
        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            throw UserException.loginPasswordIncorrect();
        }

        //TODO: generate JWT

        String token = tokenService.tokenize(user);
        return token;
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(),request.getPassword(),request.getName());
        return userMapper.toRegisterResponse(user);

    }

    public String refreshToken() throws BaseException{
        Optional<String> opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()){
            throw UserException.unAuthorized();
        }
        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);
        if(opt.isEmpty()){
            throw UserException.notFound();
        }
        User user = optUser.get();
        return tokenService.tokenize(user);
    }
}
