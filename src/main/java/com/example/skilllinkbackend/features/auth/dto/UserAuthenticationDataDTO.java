package com.example.skilllinkbackend.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDataDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
