package com.example.skilllinkbackend.features.project.validations.creation;

import com.example.skilllinkbackend.features.project.dto.ProjectRegisterDTO;

public interface ProjectCreationValidation {
    void validate(ProjectRegisterDTO projectRegisterDTO);
}
