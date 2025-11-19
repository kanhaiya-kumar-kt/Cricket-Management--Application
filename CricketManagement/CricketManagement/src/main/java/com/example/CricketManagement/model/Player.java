package com.example.CricketManagement.model;




import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true, nullable = false)
    private String registrationNumber; // Must be unique (belongs to only one team)

    private String mobileNumber;
    private String branch;
    private Integer year;
    private String section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Team team;

    private String qualifierPool; // e.g., "A1", "A2", etc.
}