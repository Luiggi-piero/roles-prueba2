package com.example.skilllinkbackend.features.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterRequestDTO(
        @NotBlank
        @Size(min = 2, max = 30)
        String firstName,

        @NotBlank
        @Size(min = 2, max = 50)
        String lastName,

        @NotBlank
        @Size(min = 2, max = 500)
        String goals,

        @NotBlank
        @Email
        String email,

        @NotNull
        @Size(min = 8, max = 15, message = "La contraseña debe tener entre 8 y 15 caracteres")
        String password,

        @NotBlank
        @Size(min = 2, max = 500)
        String biography,

        @NotBlank
        @Size(min = 2, max = 150)
        String photo
) {
}
