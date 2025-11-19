package com.example.CricketManagement.repository;


import com.example.CricketManagement.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Requirement validation: ensure player belongs to only one team
    boolean existsByRegistrationNumber(String regNo);
}