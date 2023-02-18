package com.example.test_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
