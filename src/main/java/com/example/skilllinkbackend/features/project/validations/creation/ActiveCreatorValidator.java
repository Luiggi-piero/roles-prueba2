package com.example.skilllinkbackend.features.project.validations.creation;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.project.dto.ProjectRegisterDTO;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ActiveCreatorValidator implements ProjectCreationValidation {

    private final IUserRepository userRepository;

    public ActiveCreatorValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(ProjectRegisterDTO projectRegisterDTO) {
        userRepository.findByUserId(projectRegisterDTO.creatorId())
                .orElseThrow(() -> new NotFoundException("Creador no encontrado"));
    }
}
