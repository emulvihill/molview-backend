package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @Autowired
    private AIService AIService;

    @GetMapping("/ask")
    public String askQuestion(@RequestParam String question) {
        return AIService.askQuestion(question);
    }
}