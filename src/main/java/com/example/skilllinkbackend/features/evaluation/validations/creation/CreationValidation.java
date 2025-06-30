package com.example.skilllinkbackend.features.evaluation.validations.creation;

import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;

public interface CreationValidation {
    void validate(EvaluationRegisterDTO evaluationDto);
}
