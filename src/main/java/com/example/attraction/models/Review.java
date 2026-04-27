package com.example.attraction.models;

import java.util.*;

public class Review {
    public static Map<Long, Review> reviewList = new HashMap<>();

    private Long id;
    private int rating;
    private String comment;

    public Review(int rating, String comment) {
        this.id = Math.abs(new Random().nextLong());
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
