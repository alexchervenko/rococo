package io.student.rococo.controller;

import io.student.rococo.model.ArtistJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    @GetMapping
    public Page<ArtistJson> getAllArtists(@AuthenticationPrincipal Jwt principal, @PageableDefault Pageable pageable) {
        return new PageImpl<>(List.of(
                new ArtistJson(
                        UUID.randomUUID(), "Le Artist", "Artists that no one knows about", null
                ),
                new ArtistJson(
                        UUID.randomUUID(), "Le Artist 2", "Second artists that no one knows about", null
                )
        ), pageable, 1);
    }

    @GetMapping("/{id}")
    public ArtistJson getArtistById(@PathVariable String id) {
        return new ArtistJson(
                UUID.fromString(id), "Le Artist", "Artists that no one knows about", null
        );
    }

    @GetMapping(params = "name")
    public Page<ArtistJson> searchArtistsByName(@RequestParam String name, @PageableDefault Pageable pageable) {
        return new PageImpl<>(List.of(
                new ArtistJson(
                        UUID.randomUUID(), name, null, null
                )
        ), pageable, 1);
    }

    @PostMapping
    public ArtistJson createArtist(@AuthenticationPrincipal Jwt principal, @RequestBody ArtistJson artist) {
        return artist;
    }

    @PatchMapping
    public ResponseEntity<ArtistJson> updateArtist(@AuthenticationPrincipal Jwt principal, @RequestBody ArtistJson artist) {
        return ResponseEntity.ok(artist);
    }
}

