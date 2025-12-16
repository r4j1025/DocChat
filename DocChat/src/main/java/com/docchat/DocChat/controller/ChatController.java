package com.docchat.controller;

import com.docchat.service.DocumentIngestionService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.OllamaChatModel;
import org.springframework.ai.chat.prompt.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final OllamaChatModel ollamaChatModel;
    private final VectorStore vectorStore;
    private final DocumentIngestionService ingestionService;

    public ChatController(OllamaChatModel ollamaChatModel,
                          VectorStore vectorStore,
                          DocumentIngestionService ingestionService) {
        this.ollamaChatModel = ollamaChatModel;
        this.vectorStore = vectorStore;
        this.ingestionService = ingestionService;
    }

    @PostMapping("/upload")
    public String upload(@RequestBody String document) {
        ingestionService.ingest(document);
        return "Document embedded and stored in PGVector";
    }

    @PostMapping
    public String chat(@RequestBody String message) {
        return ChatClient.builder(ollamaChatModel)
                .build()
                .prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(message)
                .call()
                .content();
    }
}
