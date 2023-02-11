package com.example.test_spring.model;

import lombok.Data;

@Data
public class MRegisterRequest {
    private String email;

    private String password;

    private String name;

}
