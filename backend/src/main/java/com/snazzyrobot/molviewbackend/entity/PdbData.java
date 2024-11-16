package com.snazzyrobot.molviewbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity(name = "PdbData")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pdb_data", schema = "molview")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdbData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    @CreatedDate
    private OffsetDateTime created;

    @Column(nullable = false)
    @LastModifiedDate
    private OffsetDateTime lastModified;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String data;

    @Column()
    private String compound;

    @Column(unique = true, nullable = false)
    private String sha256;
}