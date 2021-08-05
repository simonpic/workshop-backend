package io.wiiisdom.api.controllers;

import io.wiiisdom.api.model.User;
import io.wiiisdom.api.model.UserForm;
import io.wiiisdom.api.ressources.UserDto;
import io.wiiisdom.api.ressources.UserFormDto;
import io.wiiisdom.api.ressources.UsersDto;
import io.wiiisdom.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public UsersDto getUsers() {
        List<UserDto> users = userService.getUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new UsersDto(users);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserFormDto userFormDto) {
        UserForm userForm = modelMapper.map(userFormDto, UserForm.class);
        User user = userService.createUser(userForm);
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return modelMapper.map(user, UserDto.class);
    }

}
