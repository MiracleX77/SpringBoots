package com.example.test_spring.repository;

import com.example.test_spring.entity.Address;

import com.example.test_spring.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);


}
