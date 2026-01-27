package io.student.rococo.controller;

import io.student.rococo.model.ArtistJson;
import io.student.rococo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public Page<ArtistJson> getAllArtists(
            @RequestParam(required = false) String name,
            @PageableDefault Pageable pageable) {
        return artistService.getAllArtists(name, pageable);
    }

    @GetMapping("/{id}")
    public ArtistJson getArtistById(@PathVariable UUID id) {
        return artistService.getArtistById(id);
    }

    @PostMapping
    public ArtistJson createArtist(@AuthenticationPrincipal Jwt principal,
                                   @RequestBody ArtistJson artist) {
        return artistService.createArtist(artist);
    }

    @PatchMapping
    public ResponseEntity<ArtistJson> updateArtist(@AuthenticationPrincipal Jwt principal,
                                                   @RequestBody ArtistJson artist) {
        return ResponseEntity.ok(artistService.updateArtist(artist));
    }
}

