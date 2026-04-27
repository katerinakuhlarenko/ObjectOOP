package com.example.attraction.models.park_specified;

import org.jetbrains.annotations.Nullable;


class Attraction extends ParkEntity {


    private AttractionType type;
    private int minAge;
    private int capacity;


    public Attraction(String name, AttractionType type, @Nullable String description, String operatingHours, int minAge, int capacity) {
        super(name, description, operatingHours);
        this.type = type;
        this.minAge = minAge < 0 ? 5 : minAge;
        this.capacity = capacity;
    }

    public AttractionType getType() {
        return type;
    }

    public void setType(AttractionType type) {
        this.type = type;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void startOperation() {
        if (status == Status.OPERATING) {
            System.out.println(name + " working now.");
        } else {
            status = Status.OPERATING;
            System.out.println(name + " starting work.");
        }
    }

    @Override
    public void stopOperation() {
        if (status != Status.MAINTENANCE) {
            status = Status.MAINTENANCE;
            System.out.println(name + " now for technical service.");
        } else {
            System.out.println(name + " now turned off.");
        }
    }

    public boolean checkAge(int visitorAge) {
        boolean allowed = visitorAge >= minAge;
        System.out.println("Age " + visitorAge + " -> " + (allowed ? "allowed" : "too young"));
        return allowed;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", type=" + getType() +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", operatingHours='" + getOperatingHours() + '\'' +
                ", minAge=" + getMinAge() +
                ", capacity=" + getCapacity() +
                '}';
    }
}
