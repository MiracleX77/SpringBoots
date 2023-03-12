package com.example.test_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m-user")
public class User extends BaseEntity{

    @Column(nullable = false,unique = true,length =60)
    private String email;

    @Column(nullable = false,length =120)
    private String password;
    
    @Column(nullable = false,length =120)
    private String name;

    private String civilId;

    @OneToOne(mappedBy = "user",orphanRemoval = true)
    private Social social;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;
}
