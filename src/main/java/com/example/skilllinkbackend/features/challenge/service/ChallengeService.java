package com.example.skilllinkbackend.features.challenge.service;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeRegisterDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeResponseDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeUpdateDTO;
import com.example.skilllinkbackend.features.challenge.model.Challenge;
import com.example.skilllinkbackend.features.challenge.repository.IChallengeRepository;
import com.example.skilllinkbackend.features.usuario.model.User;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ChallengeService implements IChallengeService {

    private final IChallengeRepository challengeRepository;
    private final IUserRepository userRepository;

    public ChallengeService(IUserRepository userRepository, IChallengeRepository challengeRepository){
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }

    @Override
    public ChallengeResponseDTO createChallenge(ChallengeRegisterDTO challengeDto) {
        User creator = userRepository.findByUserId(challengeDto.creatorId())
                .orElseThrow(() -> new NotFoundException("Creador no encontrado"));
        Challenge challenge = new Challenge(challengeDto, creator);
        Challenge challengeResponse = challengeRepository.save(challenge);
        return new ChallengeResponseDTO(challengeResponse);
    }

    @Override
    public Page<ChallengeResponseDTO> findAll(Pageable pagination) {
        return challengeRepository.findAll(pagination).map(ChallengeResponseDTO::new);
    }

    @Override
    public ChallengeResponseDTO findById(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new NotFoundException("Desafío no encontrado"));
        return new ChallengeResponseDTO(challenge);
    }

    @Override
    public ChallengeResponseDTO updateChallenge(Long id, ChallengeUpdateDTO challengeDto) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Desafío no encontrado"));
        User creator = userRepository.findByUserId(challengeDto.creatorId())
                .orElseThrow(() -> new NotFoundException("Creador no encontrado"));

        challenge.update(challengeDto, creator);
        return new ChallengeResponseDTO(challenge);
    }

    @Override
    public void deleteChallenge(Long id) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Desafío no encontrado"));
        challenge.deactive();
    }
}
