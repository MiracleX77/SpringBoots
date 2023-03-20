package com.example.test_spring.bussiness;

import com.example.test_spring.model.ChatMessage;
import com.example.test_spring.model.ChatMessageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class ChatBussiness {
    private final SimpMessagingTemplate template;

    public ChatBussiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(ChatMessageRequest request){
        final String destination = "chat";

        ChatMessage payload = new ChatMessage();
        payload.setFrom("from");
        payload.setMessage(request.getMessage());

        template.convertAndSend(destination,);
    }

}
