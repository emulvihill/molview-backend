package com.snazzyrobot.molviewbackend.service;

import com.snazzyrobot.molviewbackend.repository.AtomInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtomInfoService {

    private final AtomInfoRepository atomInfoRepository;
    private final com.snazzyrobot.molviewbackend.service.AIService aiService;

    public AtomInfoService(AtomInfoRepository atomInfoRepository, com.snazzyrobot.molviewbackend.service.AIService aiService) {
        this.atomInfoRepository = atomInfoRepository;
        this.aiService = aiService;
    }

    public String getAtomInfo(String pdbFile, int atomId) {
        final String infoResult = aiService.askQuestion(
                "Give the chemistry properties of atom with id " + atomId + " in the following PDB formatted file:\n" + pdbFile
        );
        return infoResult;
    }
}
