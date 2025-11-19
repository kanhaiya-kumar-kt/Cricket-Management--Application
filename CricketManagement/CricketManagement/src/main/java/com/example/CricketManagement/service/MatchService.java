package com.example.CricketManagement.service;


import com.example.CricketManagement.model.Match;
import com.example.CricketManagement.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    /**
     * Requirement: Match scheduling.
     */
    public Match scheduleMatch(Match match) {
        return matchRepository.save(match);
    }

    /**
     * Requirement: View qualifier pool assignments.
     */
    public List<Match> getMatchesByQualifierPool(String poolName) {
        return matchRepository.findByMatchQualifierPoolOrderByMatchTimeAsc(poolName);
    }

    // CRUD: Get all matches
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}
