package com.snazzyrobot.testprojbackend.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoodInput {
    private String name;

    private String date;

    private String mood;
}