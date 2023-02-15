package com.example.test_spring.api;

import com.example.test_spring.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test_spring.bussiness.UserBusiness;
import com.example.test_spring.exception.BaseException;
import com.example.test_spring.model.MRegisterRequest;
import com.example.test_spring.model.TestResponse;

@RestController
@RequestMapping("/user")
public class UserApi {
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("Nat");
        response.setFood("KFC");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<User> register(@RequestBody MRegisterRequest request) throws BaseException {
        User response = business.register(request);
        return ResponseEntity.ok(response);
    }
}
