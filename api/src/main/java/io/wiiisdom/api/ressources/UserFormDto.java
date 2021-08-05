package io.wiiisdom.api.ressources;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserFormDto {

    @NotBlank@Email
    private String email;

    @NotBlank
    private String pseudo;

    @NotBlank@Size(min = 6)
    private String password;
}
