package com.example.skilllinkbackend.features.evaluation.validations.edition;

import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;

public interface EditionValidation {
    void validate(EvaluationUpdateDTO evaluation);
}
