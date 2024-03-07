package com.example.distributedapplication_onlinelibrary.mapper.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EBookDto {
    private final String title;
    private final String genre;
    private final double rating;
    private final int pages;
    private final BigDecimal price;
    private final boolean audioVariant;
    private final LocalDateTime whenBookAdded;
}
