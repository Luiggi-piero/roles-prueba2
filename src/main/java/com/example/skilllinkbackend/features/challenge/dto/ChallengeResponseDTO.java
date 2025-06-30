package com.example.skilllinkbackend.features.challenge.dto;

import com.example.skilllinkbackend.features.challenge.model.Challenge;
import com.example.skilllinkbackend.features.challenge.model.StatusChallenge;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationResponseDTO;
import com.example.skilllinkbackend.features.usuario.dto.UserResponseDTO;

import java.util.List;


public record ChallengeResponseDTO(
        Long id,
        String title,
        String description,
        String results,
        StatusChallenge status,
        UserResponseDTO creator,
        List<EvaluationResponseDTO> evaluations,
        boolean enabled
) {
    public ChallengeResponseDTO(Challenge challenge){
        this(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getResults(),
                challenge.getStatus(),
                new UserResponseDTO(challenge.getUser()),
                challenge.getEvaluations().stream().map(EvaluationResponseDTO::new).toList(),
                challenge.isEnabled()
        );
    }
}
