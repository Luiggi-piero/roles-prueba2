package com.example.skilllinkbackend.features.challenge.controller;

import com.example.skilllinkbackend.features.challenge.dto.ChallengeRegisterDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeResponseDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeUpdateDTO;
import com.example.skilllinkbackend.features.challenge.service.IChallengeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    private final IChallengeService challengeService;

    public ChallengeController(IChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('MENTOR')")
    public ResponseEntity<ChallengeResponseDTO> createChallenge(
            @RequestBody @Valid ChallengeRegisterDTO challengeDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        ChallengeResponseDTO challengeResponse = challengeService.createChallenge(challengeDto);
        URI url = uriComponentsBuilder.path("/challenges/{id}").buildAndExpand(challengeResponse.id()).toUri();
        return ResponseEntity.created(url).body(challengeResponse);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getChallenges(@PageableDefault(size = 10, sort = "id") Pageable pagination) {
        Page<ChallengeResponseDTO> challengesPage = challengeService.findAll(pagination);
        Map<String, Object> response = new HashMap<>();
        response.put("content", challengesPage.getContent());
        response.put("currentPage", challengesPage.getNumber());
        response.put("totalItems", challengesPage.getTotalElements());
        response.put("totalPages", challengesPage.getTotalPages());
        response.put("pageSize", challengesPage.getSize());
        response.put("hasNext", challengesPage.hasNext());
        response.put("hasPrevious", challengesPage.hasPrevious());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ChallengeResponseDTO getChallengeById(@PathVariable Long id) {
        return challengeService.findById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ChallengeResponseDTO updateChallenge(
            @PathVariable Long id,
            @RequestBody @Valid ChallengeUpdateDTO challengeDto
    ) {
        return challengeService.updateChallenge(id, challengeDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }
}
