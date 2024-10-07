package com.snazzyrobot.testprojbackend.controller;

import com.snazzyrobot.testprojbackend.entity.Mood;
import com.snazzyrobot.testprojbackend.entity.MoodInput;
import com.snazzyrobot.testprojbackend.service.MoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MoodsController {

    public MoodsController(@Autowired MoodsService moodsService) {
        this.moodsService = moodsService;
    }

    private final MoodsService moodsService;

    @QueryMapping
    public List<Mood> moods() {
        return moodsService.list();
    }

    @MutationMapping
    public Mood createMood(@Argument MoodInput mood) {
        return moodsService.createMood(mood);
    }
}