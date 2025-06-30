package com.example.skilllinkbackend.features.evaluation.dto;

import com.example.skilllinkbackend.features.evaluation.model.Evaluation;
import com.example.skilllinkbackend.features.usuario.dto.UserResponseDTO;

import java.time.LocalDateTime;

public record EvaluationResponseDTO(
        Long id,
        Double score,
        String feedback,
        LocalDateTime createdAt,
        boolean enabled,
        UserResponseDTO evaluator,
        UserResponseDTO evaluated,
        Long challengeId
) {
    public EvaluationResponseDTO(Evaluation evaluation) {
        this(
                evaluation.getId(),
                evaluation.getScore(),
                evaluation.getFeedback(),
                evaluation.getCreatedAt(),
                evaluation.isEnabled(),
                new UserResponseDTO(evaluation.getEvaluator()),
                new UserResponseDTO(evaluation.getEvaluated()),
                evaluation.getChallenge().getId()
        );
    }
}
