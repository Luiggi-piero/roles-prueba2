package com.example.skilllinkbackend.features.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRegisterDTO(
        @NotBlank
        String name
) {
}
