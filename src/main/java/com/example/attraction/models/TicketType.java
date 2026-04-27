package com.example.attraction.models;


import java.time.Duration;

public enum TicketType {
    SINGLE(Duration.ofDays(1)),
    SEASON_PASS(Duration.ofDays(30));

    private final Duration duration;

    TicketType(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
