package io.wiiisdom.api.services;

import io.wiiisdom.api.model.User;

public interface KeycloakService {
    void createUser(User user);
}
