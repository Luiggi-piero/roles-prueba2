package com.example.skilllinkbackend.features.evaluation.validations.edition;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import com.example.skilllinkbackend.features.evaluation.repository.IEvaluationRepository;
import org.springframework.stereotype.Component;

@Component
public class EditionActiveEvaluationValidator implements EditionValidation{

    private final IEvaluationRepository evaluationRepository;

    public EditionActiveEvaluationValidator(IEvaluationRepository evaluationRepository){
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public void validate(EvaluationUpdateDTO evaluation) {
        evaluationRepository.findById(evaluation.id()).orElseThrow(() -> new NotFoundException("Evaluación no encontrada"));
    }
}
