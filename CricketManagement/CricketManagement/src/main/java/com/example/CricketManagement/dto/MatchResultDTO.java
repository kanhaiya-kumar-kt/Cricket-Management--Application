package com.example.CricketManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object for updating match results and determining the winner.
 */
@Data
public class MatchResultDTO {

    @NotBlank(message = "The winner's team name is mandatory")
    private String winnerTeamName;

    // Additional fields for score tracking (optional but helpful)
    private Integer teamAScore;
    private Integer teamBScore;

    // Any notes on the match result or qualification status
    private String remarks;
}