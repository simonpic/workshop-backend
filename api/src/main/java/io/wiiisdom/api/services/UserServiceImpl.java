package io.wiiisdom.api.services;

import io.wiiisdom.api.model.User;
import io.wiiisdom.api.model.UserForm;
import io.wiiisdom.api.model.userservice.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate client;
    private final ModelMapper modelMapper;
    private final KeycloakService keycloakService;

    @Autowired
    public UserServiceImpl(@Qualifier("userMsClient") RestTemplate client,
                           ModelMapper modelMapper, KeycloakService keycloakService) {
        this.client = client;
        this.modelMapper = modelMapper;
        this.keycloakService = keycloakService;
    }

    @Override
    public List<User> getUsers() {
        Users users = client.getForObject("/users", Users.class);
        if (users == null) {
            return Collections.emptyList();
        }
        return users.getUsers().stream()
                .map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(String id) {
        String url = "/users/" + id;
        io.wiiisdom.api.model.userservice.User user = client.getForObject(url, io.wiiisdom.api.model.userservice.User.class);
        return modelMapper.map(user, User.class);
    }

    @Override
    public User createUser(UserForm userForm) {
        User user = modelMapper.map(userForm, User.class);
        user.setId(UUID.randomUUID().toString());

        keycloakService.createUser(user);

        //TODO publish use in topic
        createUserInService(user);

        return user;
    }

    private void createUserInService(User user) {
        io.wiiisdom.api.model.userservice.User body = modelMapper.map(user, io.wiiisdom.api.model.userservice.User.class);
        ResponseEntity<io.wiiisdom.api.model.userservice.User> resp =
                client.postForEntity("/users", body, io.wiiisdom.api.model.userservice.User.class);
        modelMapper.map(resp.getBody(), User.class);
    }

}
