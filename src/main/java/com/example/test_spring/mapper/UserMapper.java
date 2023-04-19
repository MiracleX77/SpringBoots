package com.example.test_spring.mapper;

import com.example.test_spring.entity.User;
import com.example.test_spring.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    MRegisterResponse toRegisterResponse(User user);
}
