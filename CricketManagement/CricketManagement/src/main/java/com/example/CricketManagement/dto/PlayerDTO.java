package com.example.CricketManagement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PlayerDTO {

    @NotBlank(message = "Player name is mandatory")
    private String name;

    @NotBlank(message = "Registration number is mandatory and unique")
    private String registrationNumber;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private String branch;
    private Integer year;
    private String section;
}