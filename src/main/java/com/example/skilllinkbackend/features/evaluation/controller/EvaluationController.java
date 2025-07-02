package com.example.skilllinkbackend.features.evaluation.controller;

import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationResponseDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import com.example.skilllinkbackend.features.evaluation.service.IEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con la evaluación de desafíos")
public class EvaluationController {

    private final IEvaluationService evaluationService;

    public EvaluationController(IEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @Operation(
            summary = "Crear una nueva evaluación",
            description = "Registra una evaluación hecha a un desafío por un mentor o evaluador.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Evaluación creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            }
    )
    @PostMapping
    @Transactional
    public ResponseEntity<EvaluationResponseDTO> createEvaluation(
            @RequestBody @Valid EvaluationRegisterDTO evaluationRegister,
            UriComponentsBuilder uriComponentsBuilder) {
        EvaluationResponseDTO evaluationResponse = evaluationService.createEvaluation(evaluationRegister);
        URI url = uriComponentsBuilder.path("/evaluations/{id}").buildAndExpand(evaluationResponse.id()).toUri();
        return ResponseEntity.created(url).body(evaluationResponse);
    }

    @Operation(
            summary = "Listar todas las evaluaciones con paginación",
            parameters = {
                    @Parameter(name = "page", description = "Número de página (0-indexado)", example = "0"),
                    @Parameter(name = "size", description = "Cantidad de elementos por página", example = "10"),
                    @Parameter(name = "sort", description = "Campo para ordenar (ej: id,asc)", example = "id,asc")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de evaluaciones obtenida exitosamente")
            }
    )
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

    @Operation(
            summary = "Obtener evaluación por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
                    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public EvaluationResponseDTO findById(@PathVariable Long id) {
        return evaluationService.findById(id);
    }

    @Operation(
            summary = "Actualizar evaluación existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluación actualizada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada", content = @Content)
            }
    )
    @PutMapping("/{id}")
    @Transactional
    public EvaluationResponseDTO updateEvaluation(
            @PathVariable Long id,
            @RequestBody @Valid EvaluationUpdateDTO evaluationDto
    ) {
        return evaluationService.updateEvaluation(id, evaluationDto);
    }

    @Operation(
            summary = "Eliminar evaluación por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Evaluación eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}
