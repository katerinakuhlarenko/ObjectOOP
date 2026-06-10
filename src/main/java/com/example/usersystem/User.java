package com.example.usersystem;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private final int id;
    private final String name;
    private final String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isLoggedIn = false;
        this.lastLoginDate = null;
    }

    public int getId()                       { return id; }
    public String getName()                  { return name; }
    public String getPassword()              { return password; }
    public LocalDateTime getLastLoginDate()  { return lastLoginDate; }
    public boolean isLoggedIn()              { return isLoggedIn; }

    public void setLastLoginDate(LocalDateTime date) { this.lastLoginDate = date; }
    public void setLoggedIn(boolean loggedIn)        { this.isLoggedIn   = loggedIn; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', loggedIn=%s, lastLogin=%s}",
                id, name, isLoggedIn, lastLoginDate);
    }
}