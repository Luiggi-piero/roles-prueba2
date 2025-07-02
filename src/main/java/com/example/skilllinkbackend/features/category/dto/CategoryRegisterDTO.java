package com.example.skilllinkbackend.features.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Datos para registrar una categoría")
public record CategoryRegisterDTO(
        @Schema(description = "Nombre de la categoría", example = "Programación")
        @NotBlank
        String name
) {
}
