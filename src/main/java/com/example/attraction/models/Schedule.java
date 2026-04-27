package com.example.attraction.models;

import com.example.attraction.ClassessStorage;
import com.example.attraction.models.park_specified.ParkEntity;
import org.jetbrains.annotations.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Schedule {
    private Long id;
    @Nullable
    private List<ParkEntity> attractions;
    private LocalTime startTime;
    private LocalTime endTime;
    @Nullable
    private List<String> specialEvents;

    public Schedule(@Nullable List<ParkEntity> attractions, LocalTime startTime, LocalTime endTime, @Nullable List<String> specialEvents) {
        this.id = Math.abs(new Random().nextLong());
        this.attractions = attractions;
        this.startTime = startTime;
        this.endTime = endTime;
        this.specialEvents = specialEvents;
        ClassessStorage.schedules.add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Nullable List<ParkEntity> getAttractions() {
        return attractions;
    }

    public void setAttractions(@Nullable List<ParkEntity> attractions) {
        this.attractions = attractions;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public @Nullable List<String> getSpecialEvents() {
        return specialEvents;
    }

    public void setSpecialEvents(@Nullable List<String> specialEvents) {
        this.specialEvents = specialEvents;
    }

    public void addAttraction(ParkEntity attraction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void removeAttraction(ParkEntity attraction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public String getAttractionWorkingHours(ParkEntity attraction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isAttractionAvailable(ParkEntity attraction, LocalTime time) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + getId() + '\'' +
                ", attractions=" + shortAttractionList(getAttractions()) +
                ", startTime=" + getStartTime() +
                ", endTime=" + getEndTime() +
                ", specialEvents=" + getSpecialEvents() +
                '}';
    }

    public String shortAttractionList(List<ParkEntity> list) {
        if (list == null) return "[]";
        List<String> ids = new ArrayList<>();
        for (ParkEntity a : list) {
            ids.add(Objects.toString(a.getId(), "null"));
        }
        return ids.toString();
    }
}