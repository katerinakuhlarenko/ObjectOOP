package com.example.usersystem;

import java.time.LocalDateTime;
import java.util.HashSet;

public class UserRegistry {

    private final HashSet<User> users = new HashSet<>();
    private int nextId = 1;

    public void registerUser(String login, String password) {
        User candidate = new User(nextId, login, password);
        if (!users.add(candidate)) {
            System.out.println("Користувач [" + login + "] вже є у списку");
            return;
        }
        nextId++;
        System.out.println("Користувач [" + login + "] зареєстрований (id=" + candidate.getId() + ")");
    }

    public boolean loginUser(String login, String password) {
        User found = findByLogin(login);
        if (found == null || !found.getPassword().equals(password)) {
            System.out.println("Неможливо ідентифікувати або аутентифікувати користувача");
            return false;
        }
        found.setLoggedIn(true);
        found.setLastLoginDate(LocalDateTime.now());
        System.out.println("Користувач [" + login + "] увійшов у систему");
        return true;
    }

    public void logoutUser(int userId) {
        for (User u : users) {
            if (u.getId() == userId) {
                u.setLoggedIn(false);
                System.out.println("Користувач [" + u.getName() + "] вийшов з системи");
                return;
            }
        }
        System.out.println("Користувача з id=" + userId + " не знайдено");
    }

    public boolean isUserRegistered(String login) {
        return findByLogin(login) != null;
    }

    public boolean removeUser(int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (removed) System.out.println("Користувача з id=" + id + " видалено");
        else         System.out.println("Користувача з id=" + id + " не знайдено");
        return removed;
    }

    public void printTotalUniqueUsers() {
        System.out.println("Кількість унікальних користувачів: " + users.size());
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Список порожній");
            return;
        }
        System.out.println("Усі користувачі:");
        for (User u : users) {
            System.out.println("  " + u);
        }
    }

    private User findByLogin(String login) {
        for (User u : users) {
            if (u.getName().equals(login)) return u;
        }
        return null;
    }
}