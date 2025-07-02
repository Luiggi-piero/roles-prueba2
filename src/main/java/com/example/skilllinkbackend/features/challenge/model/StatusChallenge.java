package com.example.skilllinkbackend.features.challenge.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estados posibles para un desafío")
public enum StatusChallenge {
    ACTIVED, CLOSED
}
