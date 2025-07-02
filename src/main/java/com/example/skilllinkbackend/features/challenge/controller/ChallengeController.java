package com.example.skilllinkbackend.features.challenge.controller;

import com.example.skilllinkbackend.features.challenge.dto.ChallengeRegisterDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeResponseDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeUpdateDTO;
import com.example.skilllinkbackend.features.challenge.service.IChallengeService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/challenges")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Desafíos", description = "Operaciones relacionadas con desafíos creados por mentores")
public class ChallengeController {

    private final IChallengeService challengeService;

    public ChallengeController(IChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Operation(
            summary = "Crear un nuevo desafío",
            description = "Permite a los mentores crear un nuevo desafío.",
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Desafío creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content)
            }
    )
    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('MENTOR')")
    public ResponseEntity<ChallengeResponseDTO> createChallenge(
            @RequestBody @Valid ChallengeRegisterDTO challengeDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        ChallengeResponseDTO challengeResponse = challengeService.createChallenge(challengeDto);
        URI url = uriComponentsBuilder.path("/challenges/{id}").buildAndExpand(challengeResponse.id()).toUri();
        return ResponseEntity.created(url).body(challengeResponse);
    }

    @Operation(
            summary = "Listar todos los desafíos con paginación",
            parameters = {
                    @Parameter(name = "page", description = "Número de página (0-indexado)", example = "0"),
                    @Parameter(name = "size", description = "Cantidad de elementos por página", example = "10"),
                    @Parameter(name = "sort", description = "Campo para ordenar (ej: id,asc)", example = "id,asc")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de desafíos obtenida exitosamente")
            }
    )
    @GetMapping
    public ResponseEntity<Map<String, Object>> getChallenges(@PageableDefault(size = 10, sort = "id") Pageable pagination) {
        Page<ChallengeResponseDTO> challengesPage = challengeService.findAll(pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("content", challengesPage.getContent());
        response.put("currentPage", challengesPage.getNumber());
        response.put("totalItems", challengesPage.getTotalElements());
        response.put("totalPages", challengesPage.getTotalPages());
        response.put("pageSize", challengesPage.getSize());
        response.put("hasNext", challengesPage.hasNext());
        response.put("hasPrevious", challengesPage.hasPrevious());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Obtener desafío por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Desafío encontrado"),
                    @ApiResponse(responseCode = "404", description = "Desafío no encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ChallengeResponseDTO getChallengeById(@PathVariable Long id) {
        return challengeService.findById(id);
    }

    @Operation(
            summary = "Actualizar desafío existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Desafío actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Desafío no encontrado", content = @Content)
            }
    )
    @PutMapping("/{id}")
    @Transactional
    public ChallengeResponseDTO updateChallenge(
            @PathVariable Long id,
            @RequestBody @Valid ChallengeUpdateDTO challengeDto
    ) {
        return challengeService.updateChallenge(id, challengeDto);
    }

    @Operation(
            summary = "Eliminar desafío por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Desafío eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Desafío no encontrado", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }
}
