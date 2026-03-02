package com.example.Spring.AI.dto;


import com.example.Spring.AI.entity.BookingStatus;

import java.time.Instant;

public record BookingResponse(Long id, String destination, Instant departureTime, BookingStatus status) {}