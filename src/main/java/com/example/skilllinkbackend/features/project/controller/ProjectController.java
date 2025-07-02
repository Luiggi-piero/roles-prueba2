package com.example.skilllinkbackend.features.project.controller;

import com.example.skilllinkbackend.features.project.dto.ProjectRegisterDTO;
import com.example.skilllinkbackend.features.project.dto.ProjectResponseDTO;
import com.example.skilllinkbackend.features.project.dto.ProjectUpdateDTO;
import com.example.skilllinkbackend.features.project.service.IProjectService;
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
@RequestMapping("/projects")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Proyectos", description = "Operaciones CRUD relacionadas con proyectos creados por los usuarios")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(
            summary = "Crear un nuevo proyecto",
            description = "Crea un nuevo proyecto a partir de los datos enviados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            }
    )
    @PostMapping
    @Transactional
    public ResponseEntity<ProjectResponseDTO> createProject(
            @RequestBody @Valid ProjectRegisterDTO projectRegisterDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        ProjectResponseDTO projectResponse = projectService.createProject(projectRegisterDTO);
        URI url = uriComponentsBuilder.path("/project/{id}").buildAndExpand(projectResponse.id()).toUri();
        return ResponseEntity.created(url).body(projectResponse);
    }

    @Operation(
            summary = "Listar todos los proyectos con paginación",
            parameters = {
                    @Parameter(name = "page", description = "Número de página (0-indexado)", example = "0"),
                    @Parameter(name = "size", description = "Cantidad de elementos por página", example = "10"),
                    @Parameter(name = "sort", description = "Campo de ordenamiento (ej: id,asc)", example = "id,asc")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de proyectos obtenida exitosamente")
            }
    )
    @GetMapping
    public Map<String, Object> getProjects(@PageableDefault(size = 10, sort = "id") Pageable pagination) {
        Page<ProjectResponseDTO> projectPage = projectService.getProjects(pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("content", projectPage.getContent());
        response.put("currentPage", projectPage.getNumber());
        response.put("totalItems", projectPage.getTotalElements());
        response.put("totalPages", projectPage.getTotalPages());
        response.put("pageSize", projectPage.getSize());
        response.put("hasNext", projectPage.hasNext());
        response.put("hasPrevious", projectPage.hasPrevious());
        return response;
    }

    @Operation(
            summary = "Obtener un proyecto por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Proyecto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Proyecto no encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ProjectResponseDTO findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @Operation(
            summary = "Actualizar un proyecto existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Proyecto no encontrado", content = @Content)
            }
    )
    @PutMapping("/{id}")
    @Transactional
    public ProjectResponseDTO updateProject(
            @PathVariable Long id,
            @RequestBody @Valid ProjectUpdateDTO projectUpdateDTO) {
        return projectService.updateProject(id, projectUpdateDTO);
    }

    @Operation(
            summary = "Eliminar un proyecto por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Proyecto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Proyecto no encontrado", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
