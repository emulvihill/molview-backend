package com.snazzyrobot.molviewbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity(name = "PdbData")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pdb_data")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class PdbData implements EntityDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    @ToString.Exclude
    private OffsetDateTime created;

    @Column(name = "modified", nullable = false)
    @LastModifiedDate
    @ToString.Exclude
    private OffsetDateTime modified;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String data;

    @Column()
    private String compound;

    @Column(unique = true, nullable = false)
    private String sha256;

    @Override
    public int hashCode() {
        return Objects.hash(name, compound, sha256);
    }
}