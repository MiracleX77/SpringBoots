package com.example.test_spring.api;

import com.example.test_spring.bussiness.ChatBussiness;
import com.example.test_spring.exception.BaseException;
import com.example.test_spring.model.ChatMessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatApi {
    private final ChatBussiness bussiness;

    public ChatApi(ChatBussiness bussiness) {
        this.bussiness = bussiness;
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody ChatMessageRequest request) throws BaseException {
        bussiness.post(request);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
