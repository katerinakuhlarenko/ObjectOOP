package com.example.attraction.models.park_specified;

import com.example.attraction.ClassessStorage;
import com.example.attraction.models.user_specified.Staff;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ParkEntity {
    protected Long id;
    protected String name;
    protected List<Staff> assignedStaff;
    @Nullable
    protected String description;
    protected Status status;
    protected String operatingHours;

    public ParkEntity(String name, @Nullable String description, String operatingHours) {
        this.id = Math.abs(new Random().nextLong());
        this.name = name;
        this.assignedStaff = new ArrayList<>();
        this.description = description;
        this.status = Status.TEMP_CLOSED;
        this.operatingHours = operatingHours;
        ClassessStorage.parkEntities.add(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Nullable String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public abstract void startOperation();

    public abstract void stopOperation();

    public void assignStaff(Staff staff) {
        System.out.println("Personal " + staff + " assigned to attraction " + name);
        this.assignedStaff.add(staff);
    }

    public List<Staff> getAssignedStaff() {
        return assignedStaff;
    }

    public static ParkEntity getAttractionById(Long attractionId) {
        for (ParkEntity attraction : ClassessStorage.parkEntities) {
            if (attraction.getId().equals(attractionId)) {
                return attraction;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ParkEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }


}
