package com.example.test_spring.api;

import com.example.test_spring.model.MLoginRequest;
import com.example.test_spring.model.MLoginResponse;
import com.example.test_spring.model.MRegisterResponse;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.test_spring.bussiness.UserBusiness;
import com.example.test_spring.exception.BaseException;
import com.example.test_spring.model.MRegisterRequest;


@RestController
@RequestMapping("/user")
public class UserApi {
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<MLoginResponse> login(@RequestBody MLoginRequest request) throws BaseException {
        MLoginResponse response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException{
        String response = business.refreshToken();
        return ResponseEntity.ok(response);
    }
}
