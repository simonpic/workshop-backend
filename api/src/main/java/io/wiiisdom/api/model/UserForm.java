package io.wiiisdom.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {
    private String email;
    private String pseudo;
    private String password;
}
