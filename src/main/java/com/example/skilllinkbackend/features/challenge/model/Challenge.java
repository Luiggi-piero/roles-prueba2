package com.example.skilllinkbackend.features.challenge.model;

import com.example.skilllinkbackend.features.challenge.dto.ChallengeRegisterDTO;
import com.example.skilllinkbackend.features.challenge.dto.ChallengeUpdateDTO;
import com.example.skilllinkbackend.features.evaluation.model.Evaluation;
import com.example.skilllinkbackend.features.usuario.model.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "challenge")
@Entity(name = "Challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String results;

    @Enumerated(EnumType.STRING)
    private StatusChallenge status;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations = new ArrayList<>();

    private boolean enabled = true;

    public Challenge() {
    }

    public Challenge(String title, String description, String results, StatusChallenge status, User user, List<Evaluation> evaluations) {
        this.title = title;
        this.description = description;
        this.results = results;
        this.status = status;
        this.user = user;
        this.evaluations = evaluations;
    }

    public Challenge(ChallengeRegisterDTO challengeRegisterDTO, User creator) {
        this.title = challengeRegisterDTO.title();

        if(challengeRegisterDTO.description() != null && !challengeRegisterDTO.description().isBlank()) {
            this.description = challengeRegisterDTO.description();
        }

        if(challengeRegisterDTO.results() != null && !challengeRegisterDTO.results().isBlank()){
            this.results = challengeRegisterDTO.results();
        }

        this.status = challengeRegisterDTO.status();
        this.user = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public StatusChallenge getStatus() {
        return status;
    }

    public void setStatus(StatusChallenge status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void update(ChallengeUpdateDTO challengeDto, User creator) {
        this.id = challengeDto.id();
        this.title = challengeDto.title();

        if(challengeDto.description() != null) {
            this.description = challengeDto.description();
        }
        if(challengeDto.results() != null) {
            this.results = challengeDto.results();
        }
        this.status = challengeDto.status();
        this.user = creator;
    }

    public void deactive() {
        this.enabled = false;
    }
}
