package com.example.CricketManagement.controller;


import com.example.CricketManagement.model.Match;
import com.example.CricketManagement.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Requirement: Match scheduling.
     * Endpoint: POST /api/matches
     */
    @PostMapping
    public ResponseEntity<Match> scheduleMatch(@RequestBody Match match) {
        Match Match = null;
        Match scheduledMatch = matchService.scheduleMatch(Match);
        return new ResponseEntity<>(scheduledMatch, HttpStatus.CREATED);
    }

    /**
     * Requirement: View qualifier pool assignments.
     * Endpoint: GET /api/matches/pool/A1
     */
    @GetMapping("/pool/{poolName}")
    public List<Match> getMatchesByQualifierPool(@PathVariable String poolName) {
        return matchService.getMatchesByQualifierPool(poolName);
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }
}