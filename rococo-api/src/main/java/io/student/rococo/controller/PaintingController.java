package io.student.rococo.controller;

import io.student.rococo.model.PaintingJson;
import io.student.rococo.service.PaintingService;
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
@RequestMapping("/api/painting")
public class PaintingController {
    private final PaintingService paintingService;

    @Autowired
    public PaintingController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping("/{id}")
    public PaintingJson getPaintingById(@PathVariable UUID id) {
        return paintingService.getPaintingById(id);
    }

    @GetMapping
    public Page<PaintingJson> getAllPaintings(
            @RequestParam(required = false) String title,
            @PageableDefault Pageable pageable) {
        return paintingService.getAllPaintings(title, pageable);
    }

    @GetMapping("/author/{artistId}")
    public Page<PaintingJson> getPaintingsByArtist(@PathVariable UUID artistId, @PageableDefault Pageable pageable) {
        return paintingService.getPaintingsByArtist(artistId, pageable);
    }

    @PostMapping
    public PaintingJson createPainting(@AuthenticationPrincipal Jwt principal, @RequestBody PaintingJson painting) {
        return paintingService.createPainting(painting);
    }

    @PatchMapping
    public ResponseEntity<PaintingJson> updatePainting(@AuthenticationPrincipal Jwt principal, @RequestBody PaintingJson painting) {
        return ResponseEntity.ok(paintingService.updatePainting(painting));
    }
}
