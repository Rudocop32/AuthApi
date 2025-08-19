package org.pet.authapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class RegisterRequest {


    @Getter
    @NotBlank
    @Size(min=3, max=64)
    private String login;

    @Getter
    @NotBlank @Size(min=6, max=72)
    private String password;


}