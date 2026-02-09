package io.spring.ai.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.spring.ai.service.EmbeddingService;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmbeddingController {

    @Autowired
    private EmbeddingService embeddingService;

    @PostMapping(path = "/ai/embedding", consumes = "text/plain", produces = "text/json")
    public ResponseEntity<String> embed(@RequestBody String message) {
        EmbeddingResponse embeddingResponse = embeddingService.getEmbeddings(message);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return ResponseEntity.ok(gson.toJson(embeddingResponse.getResult()));
    }
}
