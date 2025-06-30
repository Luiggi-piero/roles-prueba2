package com.example.skilllinkbackend.features.project.validations.edition;

import com.example.skilllinkbackend.features.project.dto.ProjectUpdateDTO;

public interface ProjectEditionValidation {
    void validate(ProjectUpdateDTO projectUpdateDTO);
}
