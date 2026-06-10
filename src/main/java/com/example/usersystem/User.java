package com.example.usersystem;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private final UserIdentifier identifier;
    private final String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;

    public User(UserIdentifier identifier, String password) {
        this.identifier = identifier;
        this.password = password;
        this.isLoggedIn = false;
        this.lastLoginDate = null;
    }

    public UserIdentifier getIdentifier()    { return identifier; }
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
        return Objects.equals(identifier, user.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return String.format("User{%s, loggedIn=%s, lastLogin=%s}",
                identifier, isLoggedIn, lastLoginDate);
    }
}