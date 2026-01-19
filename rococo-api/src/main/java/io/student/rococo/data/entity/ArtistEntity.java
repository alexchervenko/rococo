package io.student.rococo.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "artist")
public class ArtistEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String biography;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] photo;

}