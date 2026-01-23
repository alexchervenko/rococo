package io.student.rococo.data.repository;

import io.student.rococo.data.entity.MuseumEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MuseumRepository extends JpaRepository<MuseumEntity, UUID> {
    @Nonnull
    Page<MuseumEntity> findAll(@Nonnull Pageable pageable);
    
    @Nonnull
    Page<MuseumEntity> findByTitleContainingIgnoreCase(@Nonnull String title, @Nonnull Pageable pageable);
}
