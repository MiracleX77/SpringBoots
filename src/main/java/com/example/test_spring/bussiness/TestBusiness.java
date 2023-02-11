package com.example.test_spring.bussiness;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.test_spring.exception.BaseException;
import com.example.test_spring.exception.UserException;
import com.example.test_spring.model.MRegisterRequest;

@Service
public class TestBusiness {
    public String register(MRegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }
        return "";

    }
}
