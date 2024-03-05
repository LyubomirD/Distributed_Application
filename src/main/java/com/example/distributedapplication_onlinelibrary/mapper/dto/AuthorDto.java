package com.example.distributedapplication_onlinelibrary.mapper.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDto {
    private final String firstName;
    private final String lastName;
    private final LocalDate authorDateOfBirth;
    private final Boolean isDeath;
    private final LocalDate authorDateOfDeath;
    private final Double averageRating;
}
