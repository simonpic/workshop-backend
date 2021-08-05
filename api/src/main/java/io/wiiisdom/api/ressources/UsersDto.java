package io.wiiisdom.api.ressources;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersDto {
    private List<UserDto> users;
}
