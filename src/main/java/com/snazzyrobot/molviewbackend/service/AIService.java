package com.snazzyrobot.molviewbackend.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;
    private final String openaiModel;

    public AIService(ChatClient chatClient, @Value("${OPENAI_MODEL:gpt-40}") String openaiModel) {
        this.chatClient = chatClient;
        this.openaiModel = openaiModel;
    }

    public String askQuestion(String question) {
        return chatClient.prompt(openaiModel)
                .user(question)
                .call()
                .content();
    }
}