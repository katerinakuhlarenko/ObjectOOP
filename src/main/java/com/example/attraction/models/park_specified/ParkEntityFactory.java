package com.example.attraction.models.park_specified;

import java.time.LocalDateTime;

public class ParkEntityFactory {
    public static ParkEntity createAttraction(String attraction, AttractionType attractionType, String description, String operatingHours, int minAge, int capacity) {
        return new Attraction(attraction, attractionType, description, operatingHours, minAge, capacity);
    }

    public static ParkEntity createFoodStall(String foodStall, String description, String operatingHours) {
        return new FoodStall(foodStall, description, operatingHours);
    }

    public static ParkEntity createEventArea(String eventAreaName, String description, String operatingHours, String positionInPark, LocalDateTime startTime, LocalDateTime endTime) {
        return new EventArea(eventAreaName, description, operatingHours, positionInPark, startTime, endTime);
    }
}
