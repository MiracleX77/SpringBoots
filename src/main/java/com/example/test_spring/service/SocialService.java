package com.example.test_spring.service;


import com.example.test_spring.entity.Social;
import com.example.test_spring.entity.User;
import com.example.test_spring.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {
    private final SocialRepository repository;

    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user){
        return  repository.findByUser(user);
    }

    public Social create(User user,String facebook,String line,String instagram,String tiktok){
        //TODO: validate

        //create
        Social entity = new Social();
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setTiktok(tiktok);
        entity.setInstagram(instagram);
        return repository.save(entity);
    }
}
