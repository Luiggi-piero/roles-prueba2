package com.example.skilllinkbackend.features.usuario.controller;

import com.example.skilllinkbackend.config.responses.ApiResponse;
import com.example.skilllinkbackend.config.responses.DataResponse;
import com.example.skilllinkbackend.features.usuario.dto.RegisteredUserResponseDTO;
import com.example.skilllinkbackend.features.usuario.dto.UserRegisterRequestDTO;
import com.example.skilllinkbackend.features.usuario.dto.UserResponseDTO;
import com.example.skilllinkbackend.features.usuario.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getUsers(@PageableDefault(size = 10, sort = "userId") Pageable pagination) {
        Page<UserResponseDTO> usersPage = userService.getUsers(pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("content", usersPage.getContent());
        response.put("currentPage", usersPage.getNumber());
        response.put("totalItems", usersPage.getTotalElements());  // total de elementos en la bd
        response.put("totalPages", usersPage.getTotalPages());
        response.put("pageSize", usersPage.getSize());
        response.put("hasNext", usersPage.hasNext());
        response.put("hasPrevious", usersPage.hasPrevious());
        return ResponseEntity.ok(response);
    }

    @PostMapping("register")
    public ResponseEntity<DataResponse<RegisteredUserResponseDTO>> createUser(
            @RequestBody @Valid UserRegisterRequestDTO userDto) {
        RegisteredUserResponseDTO registeredUserResponseDTO =  userService.createUser(userDto);
        //ApiResponse response = new ApiResponse("Usuario registrado existosamente", HttpStatus.CREATED.value());
        DataResponse<RegisteredUserResponseDTO> response = new DataResponse(
                "Usuario registrado existosamente",
                HttpStatus.CREATED.value(),
                registeredUserResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
