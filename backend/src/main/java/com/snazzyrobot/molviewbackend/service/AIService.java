package com.snazzyrobot.molviewbackend.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String askQuestion(String question) {
        return chatClient.prompt("gpt-40")
                .user(question)
                .call()
                .content();
    }
}