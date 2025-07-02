package com.example.skilllinkbackend.features.evaluation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Schema(description = "Datos necesarios para editar una evaluación")
public record EvaluationUpdateDTO(

        @Schema(description = "Id de la evaluación", example = "4")
        @NotNull
        @Positive(message = "El id debe ser un número positivo")
        Long id,

        @Schema(description = "Puntaje de la evaluación", example = "4")
        @Min(1)
        @Max(5)
        Double score,

        @Schema(description = "Comentarios de la evaluación", example = "Buen trabajo, sigue así!")
        String feedback,

        @Schema(description = "Fecha de creación", example = "2025-07-01T21:48:57")
        LocalDateTime createdAt,

        @Schema(description = "Id del evaluador", example = "1")
        @NotNull(message = "El id del evaluador es necesario")
        Long evaluatorId,

        @Schema(description = "Id del evaluado", example = "2")
        @NotNull(message = "El id del evaluado es necesario")
        Long evaluatedId,

        @Schema(description = "Id del desafío", example = "4")
        @NotNull(message = "El id del desafío es necesario")
        Long challengeId
) {
}
