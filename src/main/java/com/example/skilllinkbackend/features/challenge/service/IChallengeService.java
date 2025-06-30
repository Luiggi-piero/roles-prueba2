package com.example.skilllinkbackend.features.challenge.service;

import com.example.skilllinkbackend.features.challenge.dto.ChallengeRegisterDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeResponseDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IChallengeService {
    ChallengeResponseDTO createChallenge(ChallengeRegisterDTO challenge);

    Page<ChallengeResponseDTO> findAll(Pageable pagination);

    ChallengeResponseDTO findById(Long challengeId);

    ChallengeResponseDTO updateChallenge(Long id, ChallengeUpdateDTO challenge);

    void deleteChallenge(Long id);
}