package com.example.skilllinkbackend.features.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Datos necesarios para crear un proyecto")
public record ProjectUpdateDTO(

        @Schema(description = "Id del proyecto", example = "1")
        @NotNull
        Long id,

        @Schema(description = "Id del creador", example = "4")
        @NotNull
        Long creatorId,

        @Schema(description = "Nombre del proyecto", example = "Calculadora Stylgar")
        @NotBlank
        String name,

        @Schema(description = "Descripción del proyecto", example = "Calculadora funcional con las operaciones básicas")
        @NotBlank
        String description,

        @Schema(description = "Fecha de inicio", example = "2025-07-01T21:48:57")
        @NotNull
        LocalDateTime startDate,

        @Schema(description = "Fecha final", example = "2025-08-01T21:48:57")
        @NotNull
        LocalDateTime endDate,

        @Schema(description = "Visibilidad del proyecto. Posibles valores: publico, privado", example = "publico")
        @NotBlank
        String visibility,

        // correos de los miembros
        @Schema(
                description = "Lista de correos de los estudiantes miembros del proyecto",
                example = "[\"alumno1@example.com\", \"alumno2@example.com\"]"
        )
        List<String> members,

        @Schema(
                description = "Lista de IDs de categorías asociadas al proyecto",
                example = "[1, 3, 5]"
        )
        List<Long> categoriesId
) {
}
