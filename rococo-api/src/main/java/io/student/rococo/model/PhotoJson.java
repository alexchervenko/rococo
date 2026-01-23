package io.student.rococo.model;

import java.util.UUID;

public record PhotoJson(UUID id, String title, String description, String photo) {
}
