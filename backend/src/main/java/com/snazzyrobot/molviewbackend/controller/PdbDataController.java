package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.service.PdbDataService;
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
    private PdbDataService pdbDataService;

    @QueryMapping(name = "allPdbData")
    public List<PdbData> allPdbData() {
        return pdbDataService.getAllPdbData();
    }

    @MutationMapping(name = "createPdbData")
    public PdbData createPdbData(@Argument CreatePdbDataInput input) {
        return pdbDataService.createPdbData(input.getName(), input.getData(), input.getCompound());
    }

    @QueryMapping(name = "findPdbDataById")
    public Optional<PdbData> findPdbDataById(@Argument long id) {
        return pdbDataService.getPdbDataById(id);
    }

    @MutationMapping(name = "deletePdbData")
    public Optional<PdbData> deletePdbData(@Argument long id) {
        return pdbDataService.deletePdbData(id);
    }
}