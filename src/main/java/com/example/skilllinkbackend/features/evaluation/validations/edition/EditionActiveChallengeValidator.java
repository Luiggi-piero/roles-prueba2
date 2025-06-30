package com.example.skilllinkbackend.features.evaluation.validations.edition;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.challenge.repository.IChallengeRepository;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class EditionActiveChallengeValidator implements EditionValidation {
    private final IChallengeRepository challengeRepository;

    public EditionActiveChallengeValidator(IChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public void validate(EvaluationUpdateDTO evaluation) {
        challengeRepository.findById(evaluation.challengeId())
                .orElseThrow(() -> new NotFoundException("Desafío no encontrado"));
    }
}
