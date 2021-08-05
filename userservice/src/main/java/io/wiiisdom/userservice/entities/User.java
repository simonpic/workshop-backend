package io.wiiisdom.userservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class User {
    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String pseudo;

    public User() { }

    public User(String email, String pseudo) {
        this.email = email;
        this.pseudo = pseudo;
    }

    public User(String id, String email, String pseudo) {
        this(email, pseudo);
        this.id = id;
    }
}
