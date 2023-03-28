package com.example.test_spring.bussiness;

import com.example.test_spring.exception.BaseException;
import com.example.test_spring.exception.ChatException;
import com.example.test_spring.model.ChatMessage;
import com.example.test_spring.model.ChatMessageRequest;
import com.example.test_spring.utill.SecurityUtill;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ChatBussiness {
    private final SimpMessagingTemplate template;

    public ChatBussiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(ChatMessageRequest request) throws BaseException {
        Optional<String>opt = SecurityUtill.getCurrentUserId();
        if(opt.isEmpty()) {
            throw ChatException.accessDenied();
        }
        final String destination = "chat";

        ChatMessage payload = new ChatMessage();
        payload.setFrom(opt.get());
        payload.setMessage(request.getMessage());

        template.convertAndSend(destination,payload);
    }

}
