package com.example.skilllinkbackend.features.challenge.dto;

import com.example.skilllinkbackend.features.challenge.model.StatusChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos necesarios para crear un desafío")
public record ChallengeRegisterDTO(
        @Schema(description = "Título del desafío", example = "Desafío de Java Básico")
        @NotBlank
        String title,

        @Schema(description = "Descripción del desafío", example = "Aplicando los principios de Java")
        String description,

        @Schema(description = "Resultados del desafío", example = "El 100% de los alumnos deben completar el desafío")
        String results,

        @Schema(
                description = "Estado del desafío. Valores posibles: ACTIVED, CLOSED",
                example = "ACTIVED"
        )
        @NotNull
        StatusChallenge status,

        @Schema(
                description = "ID del creador del desafío (mentor responsable)",
                example = "42"
        )
        @NotNull
        Long creatorId
) {
}
