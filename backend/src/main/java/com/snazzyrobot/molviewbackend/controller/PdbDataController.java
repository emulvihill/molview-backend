package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.repository.PdbDataRepository;
import com.snazzyrobot.molviewbackend.utility.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PdbDataController {

    @Autowired
    private PdbDataRepository pdbDataRepository;

    @QueryMapping(name = "allPdbData")
    public List<PdbData> allPdbData() {
        return pdbDataRepository.findAll();
    }

    @MutationMapping(name = "createPdbData")
    public PdbData createPdbData(@Argument CreatePdbDataInput input) {
        PdbData pdbData = new PdbData();
        pdbData.setName(input.getName());
        pdbData.setData(input.getData());
        pdbData.setCompound(input.getCompound());
        pdbData.setSha256(HashUtil.calculateSHA256(input.getData()));
        return pdbDataRepository.save(pdbData);
    }

    @QueryMapping(name = "findPdbDataById")
    public Optional<PdbData> findPdbDataById(@Argument int id) {
        return pdbDataRepository.findById(id);
    }

    @MutationMapping(name = "deletePdbData")
    public void deletePdbData(@Argument int id) {
        if (pdbDataRepository.existsById(id)) {
            pdbDataRepository.deleteById(id);
        }
    }
}