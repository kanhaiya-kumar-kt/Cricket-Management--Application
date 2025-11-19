package com.example.CricketManagement.repository;

import com.example.CricketManagement.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    // Custom query to view qualifier pool assignments
    List<Match> findByMatchQualifierPoolOrderByMatchTimeAsc(String poolName);
}
