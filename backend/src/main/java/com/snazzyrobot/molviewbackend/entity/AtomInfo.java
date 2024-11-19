package com.snazzyrobot.molviewbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity(name = "AtomInfo")
@Table(name = "atom_info")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class AtomInfo implements EntityDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    @ToString.Exclude
    private OffsetDateTime created;

    @Column(name = "modified")
    @LastModifiedDate
    @ToString.Exclude
    private OffsetDateTime modified;

    @ManyToOne
    @JoinColumn(name = "pdb_data_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private PdbData pdbDataId;

    @Column(nullable = false)
    private Long atomId;

    @Column(nullable = false)
    private String info;

    @Override
    public int hashCode() {
        return Objects.hash(atomId, info);
    }
}