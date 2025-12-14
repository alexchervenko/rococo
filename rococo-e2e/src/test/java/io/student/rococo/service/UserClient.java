package io.student.rococo.service;

import io.student.rococo.model.UserJson;

import java.util.Optional;

public interface UserClient {
    UserJson createUser(String username, String password);

    Optional<UserJson> findByUsername(String username);
}
