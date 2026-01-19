package io.student.rococo.service;

import io.student.rococo.data.entity.CountryEntity;
import io.student.rococo.data.entity.MuseumEntity;
import io.student.rococo.data.repository.CountryRepository;
import io.student.rococo.data.repository.MuseumRepository;
import io.student.rococo.model.CountryJson;
import io.student.rococo.model.GeoJson;
import io.student.rococo.model.MuseumJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class MuseumService {
    private final MuseumRepository museumRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public MuseumService(MuseumRepository museumRepository, CountryRepository countryRepository) {
        this.museumRepository = museumRepository;
        this.countryRepository = countryRepository;
    }

    public MuseumJson findMuseumById(UUID id) {
        return museumRepository.findById(id).map(e -> new MuseumJson(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                new String(e.getPhoto(), StandardCharsets.UTF_8),
                new GeoJson(
                        e.getCity(),
                        new CountryJson(
                                e.getCountry().getId(),
                                e.getCountry().getName()
                        )
                )
        )).orElseThrow(() -> new RuntimeException("Museum not found"));
    }

    public Page<MuseumJson> getAllMuseums(Pageable pageable) {
        return museumRepository.findAll(pageable).map(e -> new MuseumJson(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                new String(e.getPhoto(), StandardCharsets.UTF_8),
                new GeoJson(
                        e.getCity(),
                        new CountryJson(
                                e.getCountry().getId(),
                                e.getCountry().getName()
                        )
                )
        ));
    }

    @Transactional
    public MuseumJson createMuseum(MuseumJson museum) {
        MuseumEntity museumEntity = new MuseumEntity();
        museumEntity.setTitle(museum.title());
        museumEntity.setDescription(museum.description());
        museumEntity.setCity(museum.geo().city());
        museumEntity.setPhoto(museum.photo().getBytes(StandardCharsets.UTF_8));

        CountryEntity countryEntity = countryRepository.findById(museum.geo().country().id()).orElseThrow(() -> new RuntimeException("Country not found"));
        museumEntity.setCountry(countryEntity);
        MuseumEntity savedMuseum = museumRepository.save(museumEntity);

        return new MuseumJson(
                savedMuseum.getId(),
                savedMuseum.getTitle(),
                savedMuseum.getDescription(),
                new String(savedMuseum.getPhoto(), StandardCharsets.UTF_8),
                new GeoJson(
                        savedMuseum.getCity(),
                        new CountryJson(
                                savedMuseum.getCountry().getId(),
                                savedMuseum.getCountry().getName()
                        )
                )
        );
    }

    public Page<MuseumJson> findMuseumsByTitle(String title, Pageable pageable) {
        Page<MuseumEntity> museums = museumRepository.findByTitleContainingIgnoreCase(title, pageable);
        return museums.map(museum -> new MuseumJson(
                museum.getId(),
                museum.getTitle(),
                museum.getDescription(),
                new String(museum.getPhoto(), StandardCharsets.UTF_8),
                new GeoJson(
                        museum.getCity(),
                        new CountryJson(
                                museum.getCountry().getId(),
                                museum.getCountry().getName()
                        )
                )
        ));
    }

    @Transactional
    public MuseumJson updateMuseum(MuseumJson museum) {
        MuseumEntity museumEntity = museumRepository.findById(museum.id())
                .orElseThrow(() -> new RuntimeException("Museum not found"));

        museumEntity.setTitle(museum.title());
        museumEntity.setDescription(museum.description());
        museumEntity.setCity(museum.geo().city());

        if (museum.photo() != null && !museum.photo().isEmpty()) {
            museumEntity.setPhoto(museum.photo().getBytes(StandardCharsets.UTF_8));
        }

        if (!museumEntity.getCountry().getId().equals(museum.geo().country().id())) {
            CountryEntity countryEntity = countryRepository.findById(museum.geo().country().id())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            museumEntity.setCountry(countryEntity);
        }

        MuseumEntity updatedMuseum = museumRepository.save(museumEntity);

        return new MuseumJson(
                updatedMuseum.getId(),
                updatedMuseum.getTitle(),
                updatedMuseum.getDescription(),
                new String(updatedMuseum.getPhoto(), StandardCharsets.UTF_8),
                new GeoJson(
                        updatedMuseum.getCity(),
                        new CountryJson(
                                updatedMuseum.getCountry().getId(),
                                updatedMuseum.getCountry().getName()
                        )
                )
        );
    }
}
