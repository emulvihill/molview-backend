package com.snazzyrobot.testprojbackend.service;

import com.snazzyrobot.testprojbackend.entity.Mood;
import com.snazzyrobot.testprojbackend.entity.MoodInput;
import com.snazzyrobot.testprojbackend.repository.MoodsRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class MoodsService {

    private final MoodsRepository moodsRepository;

    public MoodsService(MoodsRepository moodsRepository) {
        this.moodsRepository = moodsRepository;
    }

    public List<Mood> list() {
        return moodsRepository.findAll();
    }

    public Mood createMood(MoodInput input) {
        final String inputDate = input.getDate();
        final OffsetDateTime date = inputDate != null ? OffsetDateTime.parse(inputDate) : OffsetDateTime.now();
        final Mood r = Mood.builder().name(input.getName())
                .date(date)
                .mood(input.getMood()).build();
        return moodsRepository.save(r);
    }
}