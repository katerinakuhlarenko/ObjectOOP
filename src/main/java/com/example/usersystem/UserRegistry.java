package com.example.usersystem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class UserRegistry {

    private final HashMap<UserIdentifier, User> users = new HashMap<>();
    private int nextId = 1;

    public void registerUser(String login, String password) {
        if (findByLogin(login) != null) {
            System.out.println("Користувач [" + login + "] вже є у списку");
            return;
        }
        UserIdentifier identifier = new UserIdentifier(nextId, login);
        User user = new User(identifier, password);
        users.put(identifier, user);
        nextId++;
        System.out.println("Користувач [" + login + "] зареєстрований (id=" + identifier.getId() + ")");
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
        for (User u : users.values()) {
            if (u.getIdentifier().getId() == userId) {
                u.setLoggedIn(false);
                System.out.println("Користувач [" + u.getIdentifier().getName() + "] вийшов з системи");
                return;
            }
        }
        System.out.println("Користувача з id=" + userId + " не знайдено");
    }

    public boolean isUserRegistered(String login) {
        return findByLogin(login) != null;
    }

    public boolean removeUser(int id) {
        UserIdentifier toRemove = null;
        for (UserIdentifier key : users.keySet()) {
            if (key.getId() == id) { toRemove = key; break; }
        }
        if (toRemove == null) {
            System.out.println("Користувача з id=" + id + " не знайдено");
            return false;
        }
        users.remove(toRemove);
        System.out.println("Користувача з id=" + id + " видалено");
        return true;
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
        for (User u : users.values()) {
            System.out.println("  " + u);
        }
    }

    public LinkedList<User> getUserList() {
        LinkedList<User> list = new LinkedList<>(users.values());
        list.sort((a, b) -> Integer.compare(a.getIdentifier().getId(), b.getIdentifier().getId()));
        return list;
    }

    public LinkedList<User> getInOrder(Comparator<User> comparator) {
        LinkedList<User> list = new LinkedList<>(users.values());
        list.sort(comparator);
        return list;
    }

    public LinkedList<User> getFiltered(Predicate<User> predicate) {
        LinkedList<User> result = new LinkedList<>();
        for (User u : users.values()) {
            if (predicate.test(u)) result.add(u);
        }
        return result;
    }

    public boolean saveToFile(String path) {
        try (
                FileOutputStream    fos = new FileOutputStream(path);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream  oos = new ObjectOutputStream(bos)
        ) {
            oos.writeObject(new ArrayList<>(users.values()));
            System.out.println("Збережено " + users.size() + " користувачів у файл: " + path);
            return true;
        } catch (IOException e) {
            System.err.println("Помилка збереження: " + e.getMessage());
            return false;
        }
    }

    public boolean loadFromFile(String path) {
        try (
                FileInputStream    fis = new FileInputStream(path);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream  ois = new ObjectInputStream(bis)
        ) {
            @SuppressWarnings("unchecked")
            List<User> loaded = (List<User>) ois.readObject();

            users.clear();
            int maxId = 0;
            for (User u : loaded) {
                users.put(u.getIdentifier(), u);
                if (u.getIdentifier().getId() > maxId) maxId = u.getIdentifier().getId();
            }
            nextId = maxId + 1;

            System.out.println("Відновлено " + loaded.size() + " користувачів з файлу: " + path);
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Помилка відновлення: " + e.getMessage());
            return false;
        }
    }

    private User findByLogin(String login) {
        for (User u : users.values()) {
            if (u.getIdentifier().getName().equals(login)) return u;
        }
        return null;
    }
}