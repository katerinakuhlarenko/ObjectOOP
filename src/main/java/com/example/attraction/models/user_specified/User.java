package com.example.attraction.models.user_specified;

import com.example.attraction.ClassessStorage;
import com.example.attraction.models.Ticket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public abstract class User {
    protected Long Id;
    protected String FirstName;
    protected String LastName;
    protected int Age;
    private String Email;
    private String Password;
    private UserRole Role;
    @Nullable
    private String PhotoUrl;

    public User(String firstName, String lastName, int age, String email, String password, UserRole role, @Nullable String photoUrl) {
        Id = Math.abs(new Random().nextLong());
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        Email = email;
        Password = password;
        Role = role;
        PhotoUrl = photoUrl;
        ClassessStorage.users.add(this);
    }

    public abstract void addTicket(Ticket ticket) throws Exception;

    public boolean isStaff() {
        return Role == UserRole.STAFF;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void changeRole(UserRole newRole) {
        Role = newRole;
    }

    public String getFullName() {
        return String.join(" ", FirstName, LastName);
    }

    public @Nullable String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(@NotNull String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserRole getRole() {
        return Role;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + getId() +
                ", FullName='" + getFullName() + '\'' +
                ", PhotoUrl='" + getPhotoUrl() + '\'' +
                ", Age=" + getAge() +
                ", Password='" + getPassword() + '\'' +
                ", Email='" + getEmail() + '\'' +
                ", Role=" + getRole() +
                '}';
    }
}
