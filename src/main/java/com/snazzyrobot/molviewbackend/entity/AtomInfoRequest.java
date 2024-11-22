package com.snazzyrobot.molviewbackend.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtomInfoRequest {
    private String name;

    private String date;

    private String mood;
}