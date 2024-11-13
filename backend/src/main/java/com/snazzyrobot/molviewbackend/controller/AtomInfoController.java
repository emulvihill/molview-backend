package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.service.AtomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AtomInfoController {

    public AtomInfoController(@Autowired AtomInfoService atomInfoService) {
        this.atomInfoService = atomInfoService;
    }

    private final AtomInfoService atomInfoService;

    @QueryMapping
    public String atomInfo(@Argument String pdbFile, @Argument Integer atomId) {
        return atomInfoService.getAtomInfo(pdbFile, atomId);
    }
}