package com.example.skilllinkbackend.features.project.validations.edition;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.project.dto.ProjectUpdateDTO;
import com.example.skilllinkbackend.features.project.repository.IProjectRepository;
import org.springframework.stereotype.Component;

@Component
public class EditionActiveProjectValidator implements ProjectEditionValidation {

    private final IProjectRepository projectRepository;

    public EditionActiveProjectValidator(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void validate(ProjectUpdateDTO projectUpdateDTO) {
        projectRepository.findById(projectUpdateDTO.id())
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
    }
}
