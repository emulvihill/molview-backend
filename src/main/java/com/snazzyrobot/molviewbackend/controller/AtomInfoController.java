package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.service.AtomInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AtomInfoController {

    public AtomInfoController(@Autowired AtomInfoService atomInfoService) {
        this.atomInfoService = atomInfoService;
    }

    private final AtomInfoService atomInfoService;

    @QueryMapping
    public String atomInfo(@Argument Long pdbId, @Argument Long atomId) {
        return atomInfoService.getAtomInfo(pdbId, atomId);
    }
}