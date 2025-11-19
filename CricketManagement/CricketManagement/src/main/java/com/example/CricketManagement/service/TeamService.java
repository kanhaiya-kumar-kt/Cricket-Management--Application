package com.example.CricketManagement.service;

import com.example.CricketManagement.exception.ResourceNotFoundException;
import com.example.CricketManagement.exception.ValidationException;
import com.example.CricketManagement.model.Player;
import com.example.CricketManagement.model.Team;
import com.example.CricketManagement.repository.PlayerRepository;
import com.example.CricketManagement.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    /**
     * Requirement: Register a team. Includes validation.
     */
    @Transactional
    public Team registerTeam(Team team) {
        // Validation: Player belongs to only one team
        for (Player player : team.getPlayers()) {
            if (playerRepository.existsByRegistrationNumber(player.getRegistrationNumber())) {
                throw new ValidationException("Player (Reg No: " + player.getRegistrationNumber() + ") is already registered with another team.");
            }
            player.setTeam(team);
        }

        // JPA @Size annotation in Team entity handles 13-15 players constraint.
        return teamRepository.save(team);
    }

    /**
     * Requirement: Viewing registered teams by institute.
     */
    public List<Team> getTeamsByInstitute(String instituteName) {
        return teamRepository.findByInstituteName(instituteName);
    }

    /**
     * Requirement: Generating draw sheets (batching teams into pools of eight).
     */
    @Transactional
    public List<Team> generateDrawSheets() {
        List<Team> allTeams = teamRepository.findAll();

        if (allTeams.isEmpty()) {
            throw new ResourceNotFoundException("No teams registered to create draw sheets.");
        }

        Collections.shuffle(allTeams); // Randomize team order

        int poolSize = 8;
        int numPools = (int) Math.ceil((double) allTeams.size() / poolSize);

        for (int i = 0; i < allTeams.size(); i++) {
            Team team = allTeams.get(i);
            // Assign pool number 1, 2, 3...
            team.setPoolNumber((i / poolSize) + 1);
            teamRepository.save(team);
        }
        return allTeams;
    }

    /**
     * Requirement: Displaying promotion results.
     */
    public List<Team> getPromotionResults() {
        return teamRepository.findByQualifiedIsTrueOrderByTeamNameAsc();
    }

    // CRUD: Update and Delete
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with ID: " + id));
    }

    @Transactional
    public void updateQualifierPoolAssignment(Long teamId, String pool) {
        Team team = getTeamById(teamId);
        team.setPoolNumber(Integer.parseInt(pool.substring(1))); // Assuming pool is like P1, P2
        teamRepository.save(team);
    }
}
