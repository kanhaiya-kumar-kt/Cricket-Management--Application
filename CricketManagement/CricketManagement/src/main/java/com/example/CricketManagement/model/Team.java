package com.example.CricketManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String instituteName; // VFSTR, VLITS, VPC

    private String captain;
    private String viceCaptain;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Size(min = 13, max = 15, message = "Team must have between 13 and 15 players")
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private Set<Player> players;

    private Integer poolNumber;
    private boolean qualified;  // For promotion results

    // Other details
    private String branch;
    private Integer year;
}