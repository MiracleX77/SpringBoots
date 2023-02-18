package com.example.test_spring.repository;

import com.example.test_spring.entity.Social;
import com.example.test_spring.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);


}
