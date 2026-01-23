package io.student.rococo.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "painting")
public class PaintingEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "museum_id")
    private MuseumEntity museum;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistEntity artist;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] content;
}
