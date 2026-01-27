package io.student.rococo.model;

import io.student.rococo.data.entity.UserEntity;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public record UserJson(UUID id, String username, String firstName, String lastName, String avatar) {
    public static UserJson fromEntity(UserEntity entity) {
        return new UserJson(
                entity.getId(),
                entity.getUsername(),
                entity.getFirstName(),
                entity.getLastName(),
                new String(entity.getAvatar() != null ? entity.getAvatar() : "".getBytes(StandardCharsets.UTF_8))
        );
    }
}
