package com.example.skilllinkbackend.features.evaluation.validations.creation;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.challenge.repository.IChallengeRepository;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;
import org.springframework.stereotype.Component;

@Component
public class ActiveChallengeValidator implements CreationValidation {

    public final IChallengeRepository challengeRepository;

    public ActiveChallengeValidator(IChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public void validate(EvaluationRegisterDTO evaluationDto) {
        challengeRepository.findById(evaluationDto.challengeId())
                .orElseThrow(() -> new NotFoundException("Desafío no encontrado"));
    }
}
