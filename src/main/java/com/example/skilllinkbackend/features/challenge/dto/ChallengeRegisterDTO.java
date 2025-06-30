package com.example.skilllinkbackend.features.challenge.dto;

import com.example.skilllinkbackend.features.challenge.model.StatusChallenge;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChallengeRegisterDTO(
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
