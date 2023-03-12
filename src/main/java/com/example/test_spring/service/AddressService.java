package com.example.test_spring.service;


import com.example.test_spring.entity.Address;
import com.example.test_spring.entity.User;
import com.example.test_spring.repository.AddressRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressService {
    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> findByUser(User user){
        return  repository.findByUser(user);
    }

    public Address create(User user,String line1,String line2,String zipcode){
        //TODO: validate

        //create
        Address entity = new Address();
        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);
        return repository.save(entity);
    }
}
