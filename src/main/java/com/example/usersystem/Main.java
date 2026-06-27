package com.example.usersystem;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    private static final UserRegistry registry = new UserRegistry();
    private static final Scanner     sc        = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":  handleRegister();                  break;
                case "2":  handleLogin();                     break;
                case "3":  handleLogout();                    break;
                case "4":  handleIsRegistered();              break;
                case "5":  handleRemove();                    break;
                case "6":  registry.printTotalUniqueUsers();  break;
                case "7":  registry.displayAllUsers();        break;
                case "8":  handleGetUserList();               break;
                case "9":  handleGetInOrder();                break;
                case "10": handleGetFiltered();               break;
                case "0":  System.out.println("Вихід..."); return;
                default :  System.out.println("Невірний вибір");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("─── User Registry (HashMap) ───");
        System.out.println("1.  Зареєструвати користувача");
        System.out.println("2.  Увійти у систему");
        System.out.println("3.  Вийти з системи");
        System.out.println("4.  Перевірити чи зареєстрований");
        System.out.println("5.  Видалити користувача за id");
        System.out.println("6.  Кількість унікальних користувачів");
        System.out.println("7.  Показати всіх (порядок HashMap)");
        System.out.println("8.  getUserList() — список за id");
        System.out.println("9.  getInOrder(lambda) — сортування");
        System.out.println("10. getFiltered(lambda) — фільтрація");
        System.out.println("0.  Вихід");
        System.out.print("> ");
    }

    private static void handleRegister() {
        System.out.print("Логін: ");  String login = sc.nextLine().trim();
        System.out.print("Пароль: "); String pwd   = sc.nextLine().trim();
        registry.registerUser(login, pwd);
    }

    private static void handleLogin() {
        System.out.print("Логін: ");  String login = sc.nextLine().trim();
        System.out.print("Пароль: "); String pwd   = sc.nextLine().trim();
        registry.loginUser(login, pwd);
    }

    private static void handleLogout() {
        System.out.print("ID користувача: ");
        try { registry.logoutUser(Integer.parseInt(sc.nextLine().trim())); }
        catch (NumberFormatException e) { System.out.println("Невірний формат id"); }
    }

    private static void handleIsRegistered() {
        System.out.print("Логін: ");
        String login = sc.nextLine().trim();
        System.out.println(registry.isUserRegistered(login)
                ? "Користувач [" + login + "] зареєстрований"
                : "Користувач [" + login + "] не зареєстрований");
    }

    private static void handleRemove() {
        System.out.print("ID користувача: ");
        try { registry.removeUser(Integer.parseInt(sc.nextLine().trim())); }
        catch (NumberFormatException e) { System.out.println("Невірний формат id"); }
    }

    private static void handleGetUserList() {
        LinkedList<User> list = registry.getUserList();
        if (list.isEmpty()) { System.out.println("Список порожній"); return; }
        System.out.println("Список (за id):");
        for (User u : list) System.out.println("  " + u);
    }

    private static void handleGetInOrder() {
        System.out.println("Сортувати за: 1 — id, 2 — логіном, 3 — датою входу");
        System.out.print("> ");
        String c = sc.nextLine().trim();
        Comparator<User> cmp;
        switch (c) {
            case "1":
                cmp = (a, b) -> Integer.compare(a.getIdentifier().getId(), b.getIdentifier().getId());
                break;
            case "2":
                cmp = (a, b) -> a.getIdentifier().getName().compareTo(b.getIdentifier().getName());
                break;
            case "3":
                cmp = (a, b) -> {
                    if (a.getLastLoginDate() == null && b.getLastLoginDate() == null) return 0;
                    if (a.getLastLoginDate() == null) return  1;
                    if (b.getLastLoginDate() == null) return -1;
                    return a.getLastLoginDate().compareTo(b.getLastLoginDate());
                };
                break;
            default: System.out.println("Невірний вибір"); return;
        }
        LinkedList<User> list = registry.getInOrder(cmp);
        if (list.isEmpty()) { System.out.println("Список порожній"); return; }
        System.out.println("Відсортовано:");
        for (User u : list) System.out.println("  " + u);
    }

    private static void handleGetFiltered() {
        System.out.println("Фільтр: 1 — залогінені, 2 — заходили раніше, 3 — жодного разу не входили");
        System.out.print("> ");
        String c = sc.nextLine().trim();
        Predicate<User> predicate;
        switch (c) {
            case "1": predicate = u -> u.isLoggedIn();                       break;
            case "2": predicate = u -> u.getLastLoginDate() != null;         break;
            case "3": predicate = u -> u.getLastLoginDate() == null;         break;
            default : System.out.println("Невірний вибір"); return;
        }
        LinkedList<User> list = registry.getFiltered(predicate);
        if (list.isEmpty()) { System.out.println("Нічого не знайдено"); return; }
        System.out.println("Знайдено:");
        for (User u : list) System.out.println("  " + u);
    }
}