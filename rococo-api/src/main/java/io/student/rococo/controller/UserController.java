package io.student.rococo.controller;

import io.student.rococo.model.UserJson;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping
    public UserJson getUser(@AuthenticationPrincipal Jwt principal) {
        return new UserJson(UUID.randomUUID(), principal.getClaim("sub"), "John", "Doe", null);
    }

    @PatchMapping
    public UserJson updateUser(@AuthenticationPrincipal Jwt principal, @RequestBody UserJson updateRequest) {
        return updateRequest;
    }
}
