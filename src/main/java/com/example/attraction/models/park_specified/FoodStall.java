package com.example.attraction.models.park_specified;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

class FoodStall extends ParkEntity {
    private Map<String, Double> menu;

    public FoodStall(String name, @Nullable String description, String operatingHours) {
        super(name, description, operatingHours);
        this.menu = new HashMap<>();
    }

    public Map<String, Double> getMenu() {
        return menu;
    }
    public void setMenu(Map<String, Double> menu) {
        this.menu = menu;
    }

    public void addMenuItem(String itemName, Double price) {
        this.menu.put(itemName, price);
    }

    @Override
    public void startOperation() {
        if (status == Status.OPERATING) {
            System.out.println(name + " is already open.");
        } else {
            status = Status.OPERATING;
            System.out.println(name + " is now open for business!");
        }
    }

    @Override
    public void stopOperation() {
        if (status == Status.TEMP_CLOSED) {
            System.out.println(name + " is already closed.");
        } else {
            status = Status.TEMP_CLOSED;
            System.out.println(name + " is now closed.");
        }
    }

    @Override
    public String toString() {
        return "FoodStall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", operatingHours='" + operatingHours + '\'' +
                ", menu=" + menu +
                '}';
    }

}