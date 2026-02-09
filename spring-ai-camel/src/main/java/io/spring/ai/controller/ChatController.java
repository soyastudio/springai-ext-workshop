package io.spring.ai.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.spring.ai.service.ChatService;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    ChatService chatService;

    @PostMapping(path = "/ai/chat", consumes = "text/plain", produces = "text/plain")
    public ResponseEntity<String> embed(@RequestBody String prompt) {
        return ResponseEntity.ok(chatService.getAiResponse(prompt));
    }
}
