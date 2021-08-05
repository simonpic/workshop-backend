package io.wiiisdom.userservice.controllers;

import io.wiiisdom.userservice.entities.User;
import io.wiiisdom.userservice.ressources.UserDto;
import io.wiiisdom.userservice.ressources.UsersDto;
import io.wiiisdom.userservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        List<UserDto> users = StreamSupport.stream(userService.getUsers().spliterator(), false)
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new UsersDto(users);
    }

    @PostMapping
    public UserDto postUser(@Valid @RequestBody UserDto userDto) {
        User user = userService
                .createUser(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(user, UserDto.class);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExecptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
