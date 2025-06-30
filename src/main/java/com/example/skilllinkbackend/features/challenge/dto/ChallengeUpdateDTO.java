package com.example.skilllinkbackend.features.challenge.dto;

import com.example.skilllinkbackend.features.challenge.model.StatusChallenge;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ChallengeUpdateDTO(

        @NotNull
        @Positive(message = "El id debe ser un número positivo")
        Long id,

        @NotBlank
        String title,

        String description,

        String results,

        @NotNull
        StatusChallenge status,

        @NotNull
        Long creatorId
) {
}
