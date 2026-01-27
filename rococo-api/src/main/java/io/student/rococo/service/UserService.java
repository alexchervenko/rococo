package io.student.rococo.service;

import io.student.rococo.data.entity.UserEntity;
import io.student.rococo.data.repository.UserRepository;
import io.student.rococo.model.UserJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserJson getCurrentUser(String username) {
        Optional<UserJson> userFromDB = userRepository.findByUsername(username)
                .map(UserJson::fromEntity);

        if (userFromDB.isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setFirstName("");
            user.setLastName("");
            user.setAvatar("".getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return UserJson.fromEntity(userRepository.save(user));
        } else {
            return userFromDB.get();
        }
    }
}
