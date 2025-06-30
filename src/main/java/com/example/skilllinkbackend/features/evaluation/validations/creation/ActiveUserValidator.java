package com.example.skilllinkbackend.features.evaluation.validations.creation;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ActiveUserValidator implements CreationValidation {

    private final IUserRepository userRepository;

    public ActiveUserValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(EvaluationRegisterDTO evaluationDto) {
        userRepository.findByUserId(evaluationDto.evaluatorId())
                .orElseThrow(() -> new NotFoundException("Evaluador no encontrado"));

        userRepository.findByUserId(evaluationDto.evaluatedId())
                .orElseThrow(() -> new NotFoundException("Usuario evaluado no encontrado"));
    }
}
