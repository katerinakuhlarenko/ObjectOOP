package com.example.usersystem;

import java.util.Scanner;

public class Main {

    private static final UserRegistry registry = new UserRegistry();
    private static final Scanner     sc        = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": handleRegister();                       break;
                case "2": handleLogin();                          break;
                case "3": handleLogout();                         break;
                case "4": handleIsRegistered();                   break;
                case "5": handleRemove();                         break;
                case "6": registry.printTotalUniqueUsers();       break;
                case "7": registry.displayAllUsers();             break;
                case "0": System.out.println("Вихід..."); return;
                default : System.out.println("Невірний вибір");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("─── User Registry ───");
        System.out.println("1. Зареєструвати користувача");
        System.out.println("2. Увійти у систему");
        System.out.println("3. Вийти з системи");
        System.out.println("4. Перевірити чи зареєстрований");
        System.out.println("5. Видалити користувача за id");
        System.out.println("6. Кількість унікальних користувачів");
        System.out.println("7. Показати всіх користувачів");
        System.out.println("0. Вихід");
        System.out.print("> ");
    }

    private static void handleRegister() {
        System.out.print("Логін: ");   String login = sc.nextLine().trim();
        System.out.print("Пароль: ");  String pwd   = sc.nextLine().trim();
        registry.registerUser(login, pwd);
    }

    private static void handleLogin() {
        System.out.print("Логін: ");   String login = sc.nextLine().trim();
        System.out.print("Пароль: ");  String pwd   = sc.nextLine().trim();
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
}