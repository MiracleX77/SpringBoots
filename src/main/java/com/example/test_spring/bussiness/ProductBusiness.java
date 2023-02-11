package com.example.test_spring.bussiness;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.test_spring.exception.ProductException;

@Service
public class ProductBusiness {
    public String getProductById(String id) throws ProductException {
        if (Objects.equals(id, "1234")) {
            throw ProductException.notFound();
        }
        return id;
    }
}
