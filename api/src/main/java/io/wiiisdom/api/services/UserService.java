package io.wiiisdom.api.services;

import io.wiiisdom.api.model.User;
import io.wiiisdom.api.model.UserForm;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(String id);
    User createUser(UserForm userForm);
}
