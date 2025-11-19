package com.example.CricketManagement.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class TeamRegistrationDTO {

    @NotBlank(message = "Team name is mandatory")
    private String teamName;

    @NotBlank(message = "Institute name is mandatory (e.g., VFSTR, VLITS, VPC)")
    private String instituteName;

    @NotBlank(message = "Captain name is mandatory")
    private String captain;

    private String viceCaptain;
    private String branch;
    private Integer year;

    @NotNull(message = "The team must include players")
    @Valid
    @Size(min = 13, max = 15, message = "Team must have between 13 and 15 players.")
    private Set<PlayerDTO> players;
}