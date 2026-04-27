package com.example.attraction.models.user_specified;

import com.example.attraction.models.TicketType;
import com.example.attraction.models.Review;
import com.example.attraction.models.park_specified.ParkEntity;
import com.example.attraction.models.Ticket;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
    @Nullable
    private List<Ticket> tickets;
    @Nullable
    private List<LocalDateTime> visitHistory;
    @Nullable
    private List<String> bookings;

    public Visitor(String firstName, String lastName, int age,
                   String email, String password, String photoUrl) {
        super(firstName, lastName, age, email, password, UserRole.VISITOR, photoUrl);
        this.tickets = new ArrayList<>();
        this.visitHistory = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void register(String email, String password) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean buyTicket(TicketType type) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean bookParkEntity(ParkEntity ParkEntity, String timeSlot) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public List<String> getHistory() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void leaveReview(Long parkEntityId, String reviewText, int rating) {
        Review rev = new Review(rating, reviewText);
        Review.reviewList.put(parkEntityId, rev);
    }

    public void addTicket(Ticket ticket) throws Exception {
        if (ticket != null && ticket.isActive()) {
            tickets.add(ticket);
        } else {
            throw new Exception("Cannot add ticket: tickets list is null or ticket is inactive.");
        }
    }

    public @Nullable List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(@Nullable List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public @Nullable List<LocalDateTime> getVisitHistory() {
        return visitHistory;
    }

    public void setVisitHistory(@Nullable List<LocalDateTime> visitHistory) {
        this.visitHistory = visitHistory;
    }

    public @Nullable List<String> getBookings() {
        return bookings;
    }

    public void setBookings(@Nullable List<String> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", age=" + getAge() +
                ", tickets=" + getTickets() +
                ", visitHistory=" + getVisitHistory() +
                ", bookings=" + getBookings() +
                '}';
    }
}
