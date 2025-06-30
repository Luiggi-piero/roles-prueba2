package com.example.skilllinkbackend.features.evaluation.service;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.challenge.model.Challenge;
import com.example.skilllinkbackend.features.challenge.repository.IChallengeRepository;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationResponseDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import com.example.skilllinkbackend.features.evaluation.model.Evaluation;
import com.example.skilllinkbackend.features.evaluation.repository.IEvaluationRepository;
import com.example.skilllinkbackend.features.evaluation.validations.creation.CreationValidation;
import com.example.skilllinkbackend.features.evaluation.validations.edition.EditionValidation;
import com.example.skilllinkbackend.features.usuario.model.User;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService implements IEvaluationService {

    private final IEvaluationRepository evaluationRepository;
    private final IUserRepository userRepository;
    private final IChallengeRepository challengeRepository;
    private final List<CreationValidation> creationValidators;
    private final List<EditionValidation> editionValidators;

    public EvaluationService(IEvaluationRepository evaluationRepository,
                             IUserRepository userRepository,
                             IChallengeRepository challengeRepository,
                             List<CreationValidation> creationValidators,
                             List<EditionValidation> editionValidators) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.creationValidators = creationValidators;
        this.editionValidators = editionValidators;
    }

    @Override
    public EvaluationResponseDTO createEvaluation(EvaluationRegisterDTO evaluationDto) {
        creationValidators.forEach(v -> v.validate(evaluationDto));
        User evaluator = userRepository.findByUserId(evaluationDto.evaluatorId()).get();
        User evaluated = userRepository.findByUserId(evaluationDto.evaluatedId()).get();
        Challenge challenge = challengeRepository.findById(evaluationDto.challengeId()).get();

        Evaluation evaluation = new Evaluation(evaluationDto, evaluator, evaluated, challenge);
        Evaluation evaluationResponse = evaluationRepository.save(evaluation);
        return new EvaluationResponseDTO(evaluationResponse);
    }

    @Override
    public Page<EvaluationResponseDTO> findAll(Pageable pagination) {
        return evaluationRepository.findAll(pagination).map(EvaluationResponseDTO::new);
    }

    @Override
    public EvaluationResponseDTO findById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id).orElseThrow(() -> new NotFoundException("Evaluación no encontrada"));
        return new EvaluationResponseDTO(evaluation);
    }

    @Override
    public EvaluationResponseDTO updateEvaluation(Long id, EvaluationUpdateDTO evaluationDto) {
        editionValidators.forEach(v -> v.validate(evaluationDto));
        User evaluator = userRepository.findByUserId(evaluationDto.evaluatorId()).get();
        User evaluated = userRepository.findByUserId(evaluationDto.evaluatedId()).get();
        Challenge challenge = challengeRepository.findById(evaluationDto.challengeId()).get();
        Evaluation evaluation = evaluationRepository.findById(id).get();

        evaluation.update(evaluationDto, evaluator, evaluated, challenge);
        return new EvaluationResponseDTO(evaluation);
    }

    @Override
    public void deleteEvaluation(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evaluación no encontrada"));
        evaluation.deactive();
    }


}
