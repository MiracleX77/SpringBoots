package com.example.test_spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m-address")
public class Address extends BaseEntity{

    @Column(length =120)
    private String line1;

    @Column(length =120)
    private String line2;

    @Column(length =120)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "m_user_id",nullable = false)
    private User user;

}
