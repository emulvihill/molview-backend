package com.snazzyrobot.molviewbackend.service;

import com.snazzyrobot.molviewbackend.controller.AtomInfoController;
import com.snazzyrobot.molviewbackend.repository.AtomInfoRepository;
import com.snazzyrobot.molviewbackend.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AtomInfoService {

    private final AtomInfoRepository atomInfoRepository;
    private final com.snazzyrobot.molviewbackend.service.AIService aiService;
    private final JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(AtomInfoController.class);

    public AtomInfoService(AtomInfoRepository atomInfoRepository,
                           AIService aiService,
                           JwtUtil jwtUtil) {
        this.atomInfoRepository = atomInfoRepository;
        this.aiService = aiService;
        this.jwtUtil = jwtUtil;
    }

    public String getAtomInfo(String pdbFile, int atomId) {
        String token = jwtUtil.generateToken("testuser");
        logger.warn(token);
        final String infoResult = aiService.askQuestion(
                "Give the chemistry properties of atom with id " + atomId + " in the following PDB formatted file:\n" + pdbFile
        );
        return infoResult;
    }
}
