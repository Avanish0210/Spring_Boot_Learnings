package com.example.Spring.AI.dto;

public record Joke(
        String text,
        String category,
        Double laughScore,
        Boolean isNSFW
) {
}
