package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    private final JwtService jwtService;

    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/generate-token")
    public String generateToken(@RequestParam String username) {
        return jwtService.generateToken(username);
    }
}