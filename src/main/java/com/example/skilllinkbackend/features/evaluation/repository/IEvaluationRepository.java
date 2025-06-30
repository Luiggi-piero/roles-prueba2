package com.example.skilllinkbackend.features.evaluation.repository;

import com.example.skilllinkbackend.features.evaluation.model.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("""
            SELECT e
            FROM Evaluation e
            WHERE e.enabled = true
            """)
    Page<Evaluation> findAll(Pageable pagination);

    @Query("""
            SELECT e
            FROM Evaluation e
            WHERE e.enabled = true
            AND e.id = :id
            """)
    Optional<Evaluation> findById(Long id);
}
