package io.student.rococo.controller;

import io.student.rococo.model.ArtistJson;
import io.student.rococo.model.MuseumJson;
import io.student.rococo.model.PaintingJson;
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
@RequestMapping("/api/painting")
public class PaintingController {

    @GetMapping("/{id}")
    public PaintingJson getPaintingById(@PathVariable String id) {
        return new PaintingJson(
                UUID.fromString(id),
                "Pretty Picture",
                "Description",
                null,
                new ArtistJson(UUID.randomUUID(), "Le Artist", "Artists that no one knows about", null),
                new MuseumJson(UUID.randomUUID(), "Le Museum", "Museums that no one knows about", null, null)
        );
    }

    @GetMapping
    public Page<PaintingJson> getAllPaintings(@AuthenticationPrincipal Jwt principal, @PageableDefault Pageable pageable) {
        return new PageImpl<>(List.of(
                new PaintingJson(
                        UUID.randomUUID(),
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                new PaintingJson(
                        UUID.randomUUID(),
                        null,
                        null,
                        null,
                        null,
                        null
                )
        ), pageable, 1);
    }

    @GetMapping("/author/{artistId}")
    public Page<PaintingJson> getPaintingsByArtist(@PathVariable String artistId, @PageableDefault Pageable pageable) {
        return new PageImpl<>(List.of(
                new PaintingJson(
                        UUID.randomUUID(),
                        "Le Painting",
                        null,
                        null,
                        new ArtistJson(
                                UUID.fromString(artistId),
                                "Le Artist",
                                "Artists that no one knows about",
                                null
                        ),
                        null
                )
        ), pageable, 1);
    }

    @GetMapping(params = "title")
    public Page<PaintingJson> searchPaintingsByTitle(@RequestParam String title, Pageable pageable) {
        return new PageImpl<>(List.of(
                new PaintingJson(
                        UUID.randomUUID(),
                        title,
                        null,
                        null,
                        null,
                        null
                )
        ), pageable, 1);
    }

    @PostMapping
    public PaintingJson createPainting(@AuthenticationPrincipal Jwt principal, @RequestBody PaintingJson painting) {
        return painting;
    }

    @PatchMapping
    public ResponseEntity<PaintingJson> updatePainting(@AuthenticationPrincipal Jwt principal, @RequestBody PaintingJson painting) {
        return ResponseEntity.ok(painting);
    }
}
