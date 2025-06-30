package com.example.skilllinkbackend.features.project.validations.creation;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.project.dto.ProjectRegisterDTO;
import com.example.skilllinkbackend.features.usuario.model.User;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActiveMembersValidator implements ProjectCreationValidation {

    private final IUserRepository userRepository;

    public ActiveMembersValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(ProjectRegisterDTO projectRegisterDTO) {
        List<User> existingEmails = userRepository.findExistingEmails(projectRegisterDTO.members());
        boolean allExist = existingEmails.size() == projectRegisterDTO.members().size();

        if (!allExist) {
            throw new NotFoundException("Al menos un miembro del proyecto no existe");
        }
    }
}
