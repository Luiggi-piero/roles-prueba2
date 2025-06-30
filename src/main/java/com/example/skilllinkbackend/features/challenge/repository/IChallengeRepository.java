package com.example.skilllinkbackend.features.challenge.repository;

import com.example.skilllinkbackend.features.challenge.model.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("""
            SELECT c
            FROM Challenge c
            WHERE c.enabled = true
            """)
    Page<Challenge> findAll(Pageable pagination);

    @Query("""
            SELECT c
            FROM Challenge c
            WHERE c.enabled = true AND c.id = :id
            """)
    Optional<Challenge> findById(Long id);
}
