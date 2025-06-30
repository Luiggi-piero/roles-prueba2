package com.example.skilllinkbackend.features.evaluation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record EvaluationUpdateDTO(

        @NotNull
        @Positive(message = "El id debe ser un número positivo")
        Long id,

        @Min(1)
        @Max(5)
        Double score,


        String feedback,
        LocalDateTime createdAt,

        @NotNull(message = "El id del evaluador es necesario")
        Long evaluatorId,

        @NotNull(message = "El id del evaluado es necesario")
        Long evaluatedId,

        @NotNull(message = "El id del desafío es necesario")
        Long challengeId
) {
}
