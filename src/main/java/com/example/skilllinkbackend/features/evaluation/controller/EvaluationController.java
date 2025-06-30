package com.example.skilllinkbackend.features.evaluation.controller;

import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationResponseDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import com.example.skilllinkbackend.features.evaluation.service.IEvaluationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    private final IEvaluationService evaluationService;

    public EvaluationController(IEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EvaluationResponseDTO> createEvaluation(
            @RequestBody @Valid EvaluationRegisterDTO evaluationRegister,
            UriComponentsBuilder uriComponentsBuilder) {
        EvaluationResponseDTO evaluationResponse = evaluationService.createEvaluation(evaluationRegister);
        URI url = uriComponentsBuilder.path("/evaluations/{id}").buildAndExpand(evaluationResponse.id()).toUri();
        return ResponseEntity.created(url).body(evaluationResponse);
    }

    @GetMapping
    public Map<String, Object> findAll(@PageableDefault(size = 10, sort = "id") Pageable pagination) {
        Page<EvaluationResponseDTO> evaluationsPage = evaluationService.findAll(pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("content", evaluationsPage.getContent());
        response.put("currentPage", evaluationsPage.getNumber());
        response.put("totalItems", evaluationsPage.getTotalElements());
        response.put("totalPages", evaluationsPage.getTotalPages());
        response.put("pageSize", evaluationsPage.getSize());
        response.put("hasNext", evaluationsPage.hasNext());
        response.put("hasPrevious", evaluationsPage.hasPrevious());
        return response;
    }

    @GetMapping("/{id}")
    public EvaluationResponseDTO findById(@PathVariable Long id) {
        return evaluationService.findById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public EvaluationResponseDTO updateEvaluation(
            @PathVariable Long id,
            @RequestBody @Valid EvaluationUpdateDTO evaluationDto
    ) {
        return evaluationService.updateEvaluation(id, evaluationDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}
