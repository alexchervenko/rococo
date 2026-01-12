package io.student.rococo.controller;

import io.student.rococo.model.MuseumJson;
import io.student.rococo.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/museum")
public class MuseumController {

    private final MuseumService museumService;

    @Autowired
    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping
    public Page<MuseumJson> getAllMuseums(@PageableDefault Pageable pageable) {
        return museumService.getAllMuseums(pageable);
    }

    @GetMapping("/{id}")
    public MuseumJson getMuseumById(@PathVariable String id) {
        return museumService.findMuseumById(id);
    }

    @GetMapping(params = "title")
    public Page<MuseumJson> searchMuseumsByTitle(@RequestParam String title, Pageable pageable) {
        return museumService.findMuseumsByTitle(title, pageable);
    }

    @PatchMapping
    public MuseumJson updateMuseum(@AuthenticationPrincipal Jwt principal, @RequestBody MuseumJson museumJson) {
        return museumService.updateMuseum(museumJson);
    }

    @PostMapping
    public MuseumJson createMuseum(@AuthenticationPrincipal Jwt principal, @RequestBody MuseumJson museumJson) {
        return museumService.createMuseum(museumJson);
    }
}
