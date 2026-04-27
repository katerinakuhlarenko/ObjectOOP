package com.example.attraction;

import com.example.attraction.models.Ticket;
import com.example.attraction.models.TicketType;
import com.example.attraction.models.park_specified.AttractionType;
import com.example.attraction.models.park_specified.ParkEntityFactory;
import com.example.attraction.models.user_specified.Position;
import com.example.attraction.models.park_specified.ParkEntity;
import com.example.attraction.models.user_specified.Staff;
import com.example.attraction.models.Schedule;
import com.example.attraction.models.user_specified.User;
import com.example.attraction.models.user_specified.Visitor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParkEntity attraction = ParkEntityFactory.createAttraction(
                "Roller Coaster",
                AttractionType.FAMILY,
                "A thrilling roller coaster ride.",
                "10:00 - 21:00",
                12,
                24
        );

        ParkEntity foodStall = ParkEntityFactory.createFoodStall(
                "Burger Shack",
                "Delicious burgers and fries.",
                "10:00 - 22:00"
        );

        ParkEntity eventArea = ParkEntityFactory.createEventArea(
                "Concert Stage",
                "Live music performances.",
                "18:00 - 23:00",
                "Near the main entrance",
                LocalDateTime.now(),
                LocalDateTime.of(2025, 12, 31, 10, 0)
        );



        Schedule schedule = new Schedule(
                List.of(attraction),
                LocalTime.of(10, 0),
                LocalTime.of(21, 0),
                List.of("se1", "se2")
        );

        User userStaff = new Staff("Jane", "Doe", 30, "email", "password", null, Position.TECHNICIAN, schedule);
        User userVisitor = new Visitor("Alice", "Smith", 25, "email", "password", null);

        try {
            userStaff.addTicket(new Ticket(TicketType.SINGLE, 50D, userStaff.getId()));
        } catch (Exception e ) {
            e.printStackTrace();
        }

        try {
            userVisitor.addTicket(ClassessStorage.getFirstTicket());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Staff.getVisitorTickets(userVisitor.getId()));

        System.out.println(userStaff.toString());
        System.out.println(userVisitor.toString());
        System.out.println(attraction.toString());
        System.out.println(foodStall.toString());
        System.out.println(eventArea.toString());
        System.out.println(schedule.toString());
    }
}