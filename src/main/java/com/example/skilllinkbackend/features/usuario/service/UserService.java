package com.example.skilllinkbackend.features.usuario.service;

import com.example.skilllinkbackend.config.exceptions.DuplicateResourceException;
import com.example.skilllinkbackend.features.auth.validation.password.IPasswordValidationService;
import com.example.skilllinkbackend.features.role.model.Role;
import com.example.skilllinkbackend.features.role.model.RolesEnum;
import com.example.skilllinkbackend.features.role.repository.IRoleRepository;
import com.example.skilllinkbackend.features.usuario.dto.UserRegisterRequestDTO;
import com.example.skilllinkbackend.features.usuario.dto.UserResponseDTO;
import com.example.skilllinkbackend.features.usuario.model.User;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IPasswordValidationService passwordValidationService;
    private final IRoleRepository roleRepository;

    public UserService(IUserRepository userRepository,
                       IPasswordValidationService passwordValidationService,
                       IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordValidationService = passwordValidationService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<UserResponseDTO> getUsers(Pageable pagination) {
        return userRepository.findAll(pagination).map(UserResponseDTO::new);
    }

    @Transactional
    @Override
    public void createUser(UserRegisterRequestDTO userDto) {
        validateUserUniqueness(userDto);
        passwordValidationService.validatePassword(new String(userDto.password()));
        User user = new User(userDto);
        assignDefaultRole(user);
        userRepository.save(user);
    }

    private void validateUserUniqueness(UserRegisterRequestDTO userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            throw new DuplicateResourceException("El correo electrónico ya existe");
        }
    }

    private void assignDefaultRole(User user) {
        Role role = roleRepository.findByName(RolesEnum.USER);
        user.getRoles().add(role);
    }
}
