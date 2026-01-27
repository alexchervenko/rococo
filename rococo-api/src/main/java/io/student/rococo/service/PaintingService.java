package io.student.rococo.service;

import io.student.rococo.data.entity.ArtistEntity;
import io.student.rococo.data.entity.MuseumEntity;
import io.student.rococo.data.entity.PaintingEntity;
import io.student.rococo.data.repository.ArtistRepository;
import io.student.rococo.data.repository.MuseumRepository;
import io.student.rococo.data.repository.PaintingRepository;
import io.student.rococo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaintingService {
    private final PaintingRepository paintingRepository;
    private final ArtistRepository artistRepository;
    private final MuseumRepository museumRepository;

    @Autowired
    public PaintingService(PaintingRepository paintingRepository,
                           ArtistRepository artistRepository,
                           MuseumRepository museumRepository) {
        this.paintingRepository = paintingRepository;
        this.artistRepository = artistRepository;
        this.museumRepository = museumRepository;
    }

    public Page<PaintingJson> getAllPaintings(String title, Pageable pageable) {
        if (title != null) {
            return paintingRepository.findAllByTitleContainingIgnoreCase(title, pageable)
                    .map(this::toPaintingJson);
        }
        return paintingRepository.findAll(pageable)
                .map(this::toPaintingJson);
    }

    public PaintingJson getPaintingById(UUID id) {
        PaintingEntity painting = paintingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Painting not found with id: " + id));
        return toPaintingJson(painting);
    }

    public Page<PaintingJson> getPaintingsByArtist(UUID artistId, Pageable pageable) {
        ArtistEntity artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + artistId));
        return paintingRepository.findAllByArtist(artist, pageable)
                .map(this::toPaintingJson);
    }

    public PaintingJson createPainting(PaintingJson paintingJson) {
        PaintingEntity painting = toPaintingEntity(paintingJson);
        PaintingEntity savedPainting = paintingRepository.save(painting);
        return toPaintingJson(savedPainting);
    }

    public PaintingJson updatePainting(PaintingJson paintingJson) {
        PaintingEntity existingPainting = paintingRepository.findById(paintingJson.id())
                .orElseThrow(() -> new RuntimeException("Painting not found with id: " + paintingJson.id()));

        existingPainting.setTitle(paintingJson.title());
        existingPainting.setDescription(paintingJson.description());
        if (paintingJson.content() != null && !paintingJson.content().isEmpty()) {
            existingPainting.setContent(paintingJson.content().getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }
        if (paintingJson.artist() != null) {
            ArtistEntity artist = artistRepository.findById(paintingJson.artist().id())
                    .orElseThrow(() -> new RuntimeException("Artist not found with id: " + paintingJson.artist().id()));
            existingPainting.setArtist(artist);
        }
        if (paintingJson.museum() != null) {
            MuseumEntity museum = museumRepository.findById(paintingJson.museum().id())
                    .orElseThrow(() -> new RuntimeException("Museum not found with id: " + paintingJson.museum().id()));
            existingPainting.setMuseum(museum);
        }

        PaintingEntity updatedPainting = paintingRepository.save(existingPainting);
        return toPaintingJson(updatedPainting);
    }

    private PaintingJson toPaintingJson(PaintingEntity entity) {
        String contentString = entity.getContent() != null ?
                new String(entity.getContent(), java.nio.charset.StandardCharsets.UTF_8) : null;

        ArtistJson artistJson = null;
        if (entity.getArtist() != null) {
            String artistPhotoString = entity.getArtist().getPhoto() != null ?
                    new String(entity.getArtist().getPhoto(), java.nio.charset.StandardCharsets.UTF_8) : null;
            artistJson = new ArtistJson(
                    entity.getArtist().getId(),
                    entity.getArtist().getName(),
                    entity.getArtist().getBiography(),
                    artistPhotoString
            );
        }

        MuseumJson museumJson = null;
        if (entity.getMuseum() != null) {
            String museumPhotoString = entity.getMuseum().getPhoto() != null ?
                    new String(entity.getMuseum().getPhoto(), java.nio.charset.StandardCharsets.UTF_8) : null;

            GeoJson geoJson = null;
            if (entity.getMuseum().getCountry() != null) {
                CountryJson countryJson = new CountryJson(
                        entity.getMuseum().getCountry().getId(),
                        entity.getMuseum().getCountry().getName()
                );
                geoJson = new GeoJson(entity.getMuseum().getCity(), countryJson);
            }

            museumJson = new MuseumJson(
                    entity.getMuseum().getId(),
                    entity.getMuseum().getTitle(),
                    entity.getMuseum().getDescription(),
                    museumPhotoString,
                    geoJson
            );
        }

        return new PaintingJson(entity.getId(), entity.getTitle(), entity.getDescription(), contentString, artistJson, museumJson);
    }

    private PaintingEntity toPaintingEntity(PaintingJson json) {
        PaintingEntity entity = new PaintingEntity();
        entity.setId(json.id());
        entity.setTitle(json.title());
        entity.setDescription(json.description());
        if (json.content() != null && !json.content().isEmpty()) {
            entity.setContent(json.content().getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }
        if (json.artist() != null) {
            ArtistEntity artist = artistRepository.findById(json.artist().id())
                    .orElseThrow(() -> new RuntimeException("Artist not found with id: " + json.artist().id()));
            entity.setArtist(artist);
        }
        if (json.museum() != null) {
            MuseumEntity museum = museumRepository.findById(json.museum().id())
                    .orElseThrow(() -> new RuntimeException("Museum not found with id: " + json.museum().id()));
            entity.setMuseum(museum);
        }
        return entity;
    }
}
