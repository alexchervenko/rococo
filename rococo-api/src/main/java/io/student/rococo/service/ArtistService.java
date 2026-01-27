package io.student.rococo.service;

import io.student.rococo.data.entity.ArtistEntity;
import io.student.rococo.data.repository.ArtistRepository;
import io.student.rococo.model.ArtistJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Page<ArtistJson> getAllArtists(String name, Pageable pageable) {
        if (name != null) {
            return artistRepository.findAllByNameContainingIgnoreCase(name, pageable)
                    .map(this::toArtistJson);
        }
        return artistRepository.findAll(pageable)
                .map(this::toArtistJson);
    }

    public ArtistJson getArtistById(UUID id) {
        ArtistEntity artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + id));
        return toArtistJson(artist);
    }

    public ArtistJson createArtist(ArtistJson artistJson) {
        ArtistEntity artist = toArtistEntity(artistJson);
        ArtistEntity savedArtist = artistRepository.save(artist);
        return toArtistJson(savedArtist);
    }

    public ArtistJson updateArtist(ArtistJson artistJson) {
        ArtistEntity existingArtist = artistRepository.findById(artistJson.id())
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + artistJson.id()));

        existingArtist.setName(artistJson.name());
        existingArtist.setBiography(artistJson.biography());
        if (artistJson.photo() != null && !artistJson.photo().isEmpty()) {
            existingArtist.setPhoto(artistJson.photo().getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }

        ArtistEntity updatedArtist = artistRepository.save(existingArtist);
        return toArtistJson(updatedArtist);
    }

    private ArtistJson toArtistJson(ArtistEntity entity) {
        String photoString = entity.getPhoto() != null ?
                new String(entity.getPhoto(), java.nio.charset.StandardCharsets.UTF_8) : null;
        return new ArtistJson(entity.getId(), entity.getName(), entity.getBiography(), photoString);
    }

    private ArtistEntity toArtistEntity(ArtistJson json) {
        ArtistEntity entity = new ArtistEntity();
        entity.setId(json.id());
        entity.setName(json.name());
        entity.setBiography(json.biography());
        if (json.photo() != null && !json.photo().isEmpty()) {
            entity.setPhoto(json.photo().getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }
        return entity;
    }
}
