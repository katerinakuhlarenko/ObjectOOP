package com.example.attraction.models;

import com.example.attraction.models.park_specified.AttractionType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class Ticket {
    private Long id;
    private TicketType type;
    private LocalDateTime ExpirationTime;
    private Double price;
    private Boolean isActive;
    private Long createdByUserId;

    public Ticket(TicketType type, Double price, Long userId) {
        this.id = Math.abs(new Random().nextLong());
        this.type = type;
        this.ExpirationTime = LocalDateTime.now().plus(type.getDuration());
        this.isActive = true;
        this.price = price;
        this.createdByUserId = userId;
    }

    public Long getCreatedBy() {
        return createdByUserId;
    }

    public void setCreatedBy(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Duration getDuration() {
        return type.getDuration();
    }

    public void extendExpiration(Duration amount) {
        this.ExpirationTime = this.ExpirationTime.plus(amount);
    }

    public void setAvailabilityForAttractionType(AttractionType attractionType) {

    }

    public Boolean isActive() {
        if (ExpirationTime.isBefore(LocalDateTime.now())) {
            this.isActive = false;
        }

        return isActive;
    }

    public LocalDateTime getExpirationDate() {
        return ExpirationTime;
    }
    public void setExpirationDate(LocalDateTime expirationDate) {
        ExpirationTime = expirationDate;
    }
    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
