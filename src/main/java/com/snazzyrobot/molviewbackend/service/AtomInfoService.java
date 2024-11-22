package com.snazzyrobot.molviewbackend.service;

import com.snazzyrobot.molviewbackend.controller.AtomInfoController;
import com.snazzyrobot.molviewbackend.entity.AtomInfo;
import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.repository.AtomInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtomInfoService {

    private final AtomInfoRepository atomInfoRepository;
    private final com.snazzyrobot.molviewbackend.service.AIService aiService;

    private final PdbDataService pdbDataService;

    Logger logger = LoggerFactory.getLogger(AtomInfoService.class);

    public AtomInfoService(AtomInfoRepository atomInfoRepository,
                           AIService aiService,
                           PdbDataService pdbDataService) {
        this.pdbDataService = pdbDataService;
        this.atomInfoRepository = atomInfoRepository;
        this.aiService = aiService;
    }

    public String getAtomInfo(long pdbId, long atomId) {
        PdbData pdbData = pdbDataService.getPdbDataById(pdbId).get();

        AtomInfo atomInfo = atomInfoRepository.getDistinctByAtomIdAndPdbData(atomId, pdbData);

        if (atomInfo == null) {
            final String infoResult = aiService.askQuestion(
                    "Give the chemistry properties of atom with id " + atomId + " in the following PDB formatted file:\n" + pdbData.getData()
            );
            atomInfo = new AtomInfo();
            atomInfo.setInfo(infoResult);
            atomInfo.setAtomId(atomId);
            atomInfo.setPdbData(pdbData);
            atomInfo = atomInfoRepository.save(atomInfo);
            logger.info("Saved AtomInfo: " + atomInfo);
        }
        return atomInfo.getInfo();
    }

}
