package com.example.skilllinkbackend.features.usuario.service;

import com.example.skilllinkbackend.features.usuario.dto.UserRegisterRequestDTO;
import com.example.skilllinkbackend.features.usuario.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<UserResponseDTO> getUsers(Pageable pagination);

    void createUser(UserRegisterRequestDTO userDto);
}

