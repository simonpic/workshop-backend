package io.wiiisdom.userservice.ressources;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String id;

    @NotBlank@Email
    private String email;

    @NotBlank
    private String pseudo;

}
