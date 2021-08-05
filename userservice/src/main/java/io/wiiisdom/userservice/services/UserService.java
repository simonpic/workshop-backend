package io.wiiisdom.userservice.services;

import io.wiiisdom.userservice.entities.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> getUsers();
    Optional<User> getUserById(String id);
    User createUser(User user);
}
