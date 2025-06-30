package com.example.skilllinkbackend.features.evaluation.validations.edition;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class EditionActiveUserValidator implements EditionValidation {
    private final IUserRepository userRepository;

    public EditionActiveUserValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(EvaluationUpdateDTO evaluation) {
        userRepository.findByUserId(evaluation.evaluatorId())
                .orElseThrow(() -> new NotFoundException("Evaluador no encontrado"));
        userRepository.findByUserId(evaluation.evaluatedId())
                .orElseThrow(() -> new NotFoundException("Persona evaluada no fue encontrada"));
    }

}
