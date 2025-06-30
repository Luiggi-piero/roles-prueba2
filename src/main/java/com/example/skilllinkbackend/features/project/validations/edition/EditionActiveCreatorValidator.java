package com.example.skilllinkbackend.features.project.validations.edition;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.project.dto.ProjectUpdateDTO;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class EditionActiveCreatorValidator implements ProjectEditionValidation {
    private final IUserRepository userRepository;

    public EditionActiveCreatorValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(ProjectUpdateDTO projectUpdateDTO) {
        userRepository.findByUserId(projectUpdateDTO.creatorId())
                .orElseThrow(() -> new NotFoundException("Creador no encontrado"));
    }
}
