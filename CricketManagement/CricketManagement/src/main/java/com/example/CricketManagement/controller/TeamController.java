package com.example.CricketManagement.controller;


import com.example.CricketManagement.model.Team;
import com.example.CricketManagement.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // --- CRUD ---
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    // --- REQUIREMENTS ---

    /**
     * Requirement: Register a team.
     * Endpoint: POST /api/teams
     */
    @PostMapping
    public ResponseEntity<Team> registerTeam(@Valid @RequestBody Team team) {
        Team savedTeam = teamService.registerTeam(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    /**
     * Requirement: Viewing registered teams by institute.
     * Endpoint: GET /api/teams/institute/VFSTR
     */
    @GetMapping("/institute/{instituteName}")
    public List<Team> getTeamsByInstitute(@PathVariable String instituteName) {
        return teamService.getTeamsByInstitute(instituteName);
    }

    /**
     * Requirement: Generating draw sheets (batching teams into pools of eight).
     * Endpoint: POST /api/teams/draw-sheets
     */
    @PostMapping("/draw-sheets")
    public List<Team> generateDrawSheets() {
        return teamService.generateDrawSheets();
    }

    /**
     * Requirement: Displaying promotion results.
     * Endpoint: GET /api/teams/promotions
     */
    @GetMapping("/promotions")
    public List<Team> getPromotionResults() {
        return teamService.getPromotionResults();
    }

    /**
     * Additional Endpoint: Update qualifier pool assignment
     * Endpoint: PUT /api/teams/1/qualifier/P1
     */
    @PutMapping("/{teamId}/qualifier/{pool}")
    public ResponseEntity<String> updateQualifierPool(@PathVariable Long teamId, @PathVariable String pool) {
        teamService.updateQualifierPoolAssignment(teamId, pool);
        return ResponseEntity.ok("Team " + teamId + " assigned to pool " + pool + ".");
    }
}