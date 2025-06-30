package com.example.skilllinkbackend.features.project.dto;

import com.example.skilllinkbackend.features.category.dto.CategoryResponseDTO;
import com.example.skilllinkbackend.features.project.model.Project;
import com.example.skilllinkbackend.features.usuario.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectResponseDTO(
        Long id,
        UserResponseDTO creator,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String visibility,
        boolean enabled,
        List<UserResponseDTO> members,
        List<CategoryResponseDTO> categories
) {
    public ProjectResponseDTO(Project project) {
        this(
                project.getId(),
                new UserResponseDTO(project.getCreator()),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getVisibility(),
                project.isEnabled(),
                project.getMembers().stream().map(UserResponseDTO::new).toList(),
                project.getCategories().stream().map(CategoryResponseDTO::new).toList()
        );
    }
}
