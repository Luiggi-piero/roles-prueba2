package com.example.skilllinkbackend.features.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para crear un usuario")
public record UserRegisterRequestDTO(
        @Schema(description = "Nombre del usuario", example = "Alexey")
        @NotBlank
        @Size(min = 2, max = 30)
        String firstName,

        @Schema(description = "Apellido", example = "Kovack")
        @NotBlank
        @Size(min = 2, max = 50)
        String lastName,

        @Schema(description = "Objetivos del usuario", example = "Aprender java y spring boot")
        @NotBlank
        @Size(min = 2, max = 500)
        String goals,

        @Schema(description = "Correo del usuario", example = "alexey@gmail.com")
        @NotBlank
        @Email
        String email,

        @Schema(description = "Contraseña del usuario", example = "12345678A")
        @NotNull
        @Size(min = 8, max = 15, message = "La contraseña debe tener entre 8 y 15 caracteres")
        String password,

        @Schema(description = "Biografía del usuario", example = "Estudiante de la carrera de Ingeniería Industrial")
        @NotBlank
        @Size(min = 2, max = 500)
        String biography,

        @Schema(description = "URL de la foto", example = "https://cdn-icons-png.flaticon.com/128/18851/18851112.png")
        @NotBlank
        @Size(min = 2, max = 350)
        String photo
) {
}
