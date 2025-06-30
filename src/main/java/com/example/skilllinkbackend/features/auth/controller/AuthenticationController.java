package com.example.skilllinkbackend.features.auth.controller;

import com.example.skilllinkbackend.features.auth.dto.UserAuthenticationDataDTO;
import com.example.skilllinkbackend.features.auth.model.DataJWTToken;
import com.example.skilllinkbackend.features.auth.service.TokenService;
import com.example.skilllinkbackend.features.auth.validation.password.IPasswordValidationService;
import com.example.skilllinkbackend.features.usuario.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final IPasswordValidationService passwordValidationService;

    public AuthenticationController(AuthenticationManager authenticationManager
                                    ,TokenService tokenService
                                    ,IPasswordValidationService passwordValidationService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordValidationService = passwordValidationService;
    }

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid UserAuthenticationDataDTO userAuthenticationDataDTO){
        passwordValidationService.validatePassword(new String(userAuthenticationDataDTO.password()));
        Authentication authToken = new UsernamePasswordAuthenticationToken(userAuthenticationDataDTO.email(), userAuthenticationDataDTO.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(JWTToken));
    }
}