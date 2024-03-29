package com.example.test_spring.entity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GenericGenerator(name="uuid2",strategy="uuid2")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator ="uuid2")
    @Column(length =36,nullable = false, updatable = false)
    private String id;
}
