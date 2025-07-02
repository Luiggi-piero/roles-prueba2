package com.example.skilllinkbackend.features.evaluation.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos necesarios para crear una evaluación")
public record EvaluationRegisterDTO(
        @Schema(description = "Puntaje de la evaluación", example = "4")
        @Min(1)
        @Max(5)
        @NotNull(message = "El puntaje es necesario")
        Double score,

        @Schema(description = "Comentarios de la evaluación", example = "Buen trabajo, sigue así!")
        String feedback,

        @Schema(description = "Id del evaluador", example = "1")
        @NotNull(message = "El id del evaluador es necesario")
        Long evaluatorId,

        @Schema(description = "Id del evaluado", example = "2")
        @NotNull(message = "El id del evaluado es necesario")
        Long evaluatedId,

        @Schema(description = "Id del desafío", example = "4")
        @NotNull(message = "El id del desafio es necesario")
        Long challengeId
) {
}
