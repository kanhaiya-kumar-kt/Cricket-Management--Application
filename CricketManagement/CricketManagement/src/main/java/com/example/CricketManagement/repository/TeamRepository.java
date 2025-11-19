package com.example.CricketManagement.repository;


import com.example.CricketManagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // Custom query to find registered teams by institute
    List<Team> findByInstituteName(String instituteName);

    // Custom query for promotion results (assuming 'qualified' teams are promoted)
    List<Team> findByQualifiedIsTrueOrderByTeamNameAsc();

    // Custom query to find teams by their assigned pool number
    List<Team> findByPoolNumber(Integer poolNumber);
}