package com.example.skilllinkbackend.features.usuario.repository;

import com.example.skilllinkbackend.features.usuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String username);

    boolean existsByEmail(String username);

    Optional<User> findByUserId(Long userId);

    @Query("""
            SELECT u
            FROM User u
            WHERE u.enabled = true AND u.email IN :emails
            """)
    List<User> findExistingEmails(List<String> emails);
}