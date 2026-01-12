package io.student.rococo.data.repository;

import io.student.rococo.data.entity.CountryEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    @Nonnull
    Optional<CountryEntity> findByName(@Nonnull String name);

    @Nonnull
    Page<CountryEntity> findAll(@Nonnull Pageable pageable);
}
