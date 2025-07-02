package com.example.skilllinkbackend.features.challenge.dto;

import com.example.skilllinkbackend.features.challenge.model.StatusChallenge;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Datos necesarios para editar un desafío")
public record ChallengeUpdateDTO(

        @Schema(description = "Id del desafío", example = "1")
        @NotNull
        @Positive(message = "El id debe ser un número positivo")
        Long id,

        @Schema(description = "Título del desafío", example = "Programación en C++")
        @NotBlank
        String title,

        @Schema(description = "Descripción del desafío", example = "Elaborar una calculadora")
        String description,

        @Schema(description = "Resultados del desafío", example = "Lograr un calculadora funcional")
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
