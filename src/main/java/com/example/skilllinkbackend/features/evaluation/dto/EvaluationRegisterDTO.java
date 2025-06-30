package com.example.skilllinkbackend.features.evaluation.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EvaluationRegisterDTO(
        @Min(1)
        @Max(5)
        @NotNull(message = "El puntaje es necesario")
        Double score,

        String feedback,

        @NotNull(message = "El id del evaluador es necesario")
        Long evaluatorId,

        @NotNull(message = "El id del evaluado es necesario")
        Long evaluatedId,

        @NotNull(message = "El id del desafio es necesario")
        Long challengeId
) {
}
