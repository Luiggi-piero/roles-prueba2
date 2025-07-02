package com.example.skilllinkbackend.features.category.controller;

import com.example.skilllinkbackend.features.category.dto.CategoryRegisterDTO;
import com.example.skilllinkbackend.features.category.dto.CategoryResponseDTO;
import com.example.skilllinkbackend.features.category.dto.CategoryUpdateDTO;
import com.example.skilllinkbackend.features.category.service.ICategoryService;
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
@RequestMapping("/categories")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Categorías", description = "Operaciones relacionadas con la gestión de categorías")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Crear una nueva categoría",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            }
    )
    @PostMapping
    @Transactional
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @RequestBody @Valid CategoryRegisterDTO categoryRegisterDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        CategoryResponseDTO categoryResponseDTO = categoryService.createCategory(categoryRegisterDTO);
        URI url = uriComponentsBuilder.path("/categories/{id}").buildAndExpand(categoryResponseDTO.id()).toUri();
        return ResponseEntity.created(url).body(categoryResponseDTO);
    }

    @Operation(
            summary = "Listar categorías con paginación",
            parameters = {
                    @Parameter(name = "page", description = "Número de página (0-indexado)", example = "0"),
                    @Parameter(name = "size", description = "Cantidad de elementos por página", example = "10"),
                    @Parameter(name = "sort", description = "Campo para ordenar (ej: id,asc)", example = "id,asc")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de categorías")
            }
    )
    @GetMapping
    public Map<String, Object> getCategories(@PageableDefault(size = 10, sort = "id") Pageable pagination) {
        Page<CategoryResponseDTO> categoryPage = categoryService.getCategories(pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("content", categoryPage.getContent());
        response.put("currentPage", categoryPage.getNumber());
        response.put("totalItems", categoryPage.getTotalElements());
        response.put("totalPages", categoryPage.getTotalPages());
        response.put("pageSize", categoryPage.getSize());
        response.put("hasNext", categoryPage.hasNext());
        response.put("hasPrevious", categoryPage.hasPrevious());
        return response;
    }

    @Operation(
            summary = "Obtener una categoría por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
                    @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public CategoryResponseDTO findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @Operation(
            summary = "Actualizar una categoría existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
            }
    )
    @PutMapping("/{id}")
    @Transactional
    public CategoryResponseDTO updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryUpdateDTO categoryUpdateDTO) {
        return categoryService.updateCategory(id, categoryUpdateDTO);
    }

    @Operation(
            summary = "Eliminar una categoría por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
