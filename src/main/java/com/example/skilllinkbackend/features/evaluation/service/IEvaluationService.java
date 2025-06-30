package com.example.skilllinkbackend.features.evaluation.service;

import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationResponseDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEvaluationService {
    EvaluationResponseDTO createEvaluation(EvaluationRegisterDTO evaluation);

    Page<EvaluationResponseDTO> findAll(Pageable pagination);

    EvaluationResponseDTO findById(Long id);

    EvaluationResponseDTO updateEvaluation(Long id, EvaluationUpdateDTO evaluation);

    void deleteEvaluation(Long id);
}
