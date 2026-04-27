package com.example.attraction.models.park_specified;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

class EventArea extends ParkEntity {
    private String positionInPark;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventArea(String name, @Nullable String description, String operatingHours, String positionInPark, LocalDateTime startTime, LocalDateTime endTime) {
        super(name, description, operatingHours);
        this.positionInPark = positionInPark;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPositionInPark() {
        return positionInPark;
    }

    public void setPositionInPark(String positionInPark) {
        this.positionInPark = positionInPark;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public void startOperation() {
        if (status == Status.OPERATING) {
            System.out.println(name + " event area is already open.");
        } else {
            status = Status.OPERATING;
            System.out.println(name + " event area is now open!");
        }

        if (startTime.isAfter(LocalDateTime.now())) {
            System.out.println("The event at " + name + " has started.");
        }
    }

    @Override
    public void stopOperation() {
        if (status == Status.TEMP_CLOSED) {
            System.out.println(name + " event area is already closed.");
        } else {
            status = Status.TEMP_CLOSED;
            System.out.println(name + " event area is now closed.");
        }

        if (endTime.isBefore(LocalDateTime.now())) {
            System.out.println("The event at " + name + " has ended.");
        }
    }

    @Override
    public String toString() {
        return "EventArea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", operatingHours='" + operatingHours + '\'' +
                ", positionInPark='" + positionInPark + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}