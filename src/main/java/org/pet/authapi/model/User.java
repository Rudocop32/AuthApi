package org.pet.authapi.model;

import jakarta.persistence.*;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "api_users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_username", columnNames = "login")})
@Getter
@Setter
@NoArgsConstructor

public class User {
    public User(String password, String login) {
        this.password = password;
        this.login = login;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique = true, length=64)

    private String login;

    @Column(nullable=false, length=60)
    private String password;


}