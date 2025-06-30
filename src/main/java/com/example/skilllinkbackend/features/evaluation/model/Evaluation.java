package com.example.skilllinkbackend.features.evaluation.model;

import com.example.skilllinkbackend.features.challenge.model.Challenge;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationRegisterDTO;
import com.example.skilllinkbackend.features.evaluation.dto.EvaluationUpdateDTO;
import com.example.skilllinkbackend.features.usuario.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "evaluation")
@Entity(name = "Evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double score;
    private String feedback;
    private LocalDateTime createdAt;
    private boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private User evaluator;

    @ManyToOne
    @JoinColumn(name = "evaluated_id")
    private User evaluated;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    public Evaluation() {
    }

    public Evaluation(Double score, String feedback, LocalDateTime createdAt, boolean enabled, User evaluator, User evaluated, Challenge challenge) {
        this.score = score;
        this.feedback = feedback;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.evaluator = evaluator;
        this.evaluated = evaluated;
        this.challenge = challenge;
    }

    public Evaluation(EvaluationRegisterDTO evaluationDto, User evaluator, User evaluated, Challenge challenge) {
        this.score = evaluationDto.score();

        if (evaluationDto.feedback() != null && !evaluationDto.feedback().isBlank()) {
            this.feedback = evaluationDto.feedback();
        }

        this.createdAt = LocalDateTime.now();
        this.evaluator = evaluator;
        this.evaluated = evaluated;
        this.challenge = challenge;
    }

    public Long getId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(User evaluator) {
        this.evaluator = evaluator;
    }

    public User getEvaluated() {
        return evaluated;
    }

    public void setEvaluated(User evaluated) {
        this.evaluated = evaluated;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public void update(EvaluationUpdateDTO evaluationDto, User evaluator, User evaluated, Challenge challenge) {
        this.id = evaluationDto.id();
        this.score = evaluationDto.score();
        if (evaluationDto.feedback() != null) {
            this.feedback = evaluationDto.feedback();
        }
        if (evaluationDto.createdAt() != null) {
            this.createdAt = evaluationDto.createdAt();
        }

        this.evaluator = evaluator;
        this.evaluated = evaluated;
        this.challenge = challenge;
    }

    public void deactive() {
        this.enabled = false;
    }
}
