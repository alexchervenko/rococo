package io.student.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record UserJson(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("username")
        String username,
        @JsonProperty("firstname")
        String firstName,
        @JsonProperty("lastname")
        String lastName,
        @JsonProperty("avatar")
        String avatar
) {
}
