package com.snazzyrobot.molviewbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity(name = "Mood")
@Table(name = "atomInfo", schema = "testdata")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Lob
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private OffsetDateTime date;

    @Column(nullable = false)
    private String mood;
}