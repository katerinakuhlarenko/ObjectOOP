package com.example.log;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner   sc     = new Scanner(System.in);
    private static final LogFilter filter = new SevereLogFilter();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": handleGenerate(); break;
                    case "2": handleFilter();   break;
                    case "0": System.out.println("Вихід..."); return;
                    default : System.out.println("Невірний вибір");
                }
            } catch (IOException e) {
                System.err.println("Помилка: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("─── Log Filter ───");
        System.out.println("1. Згенерувати тестовий лог-файл");
        System.out.println("2. Відфільтрувати лог-файл");
        System.out.println("0. Вихід");
        System.out.print("> ");
    }

    private static void handleGenerate() throws IOException {
        System.out.print("Шлях до вихідного файлу [test.log]: ");
        String path = sc.nextLine().trim();
        if (path.isEmpty()) path = "test.log";

        System.out.print("Кількість рядків [1000]: ");
        String n = sc.nextLine().trim();
        int count;
        try { count = n.isEmpty() ? 1000 : Integer.parseInt(n); }
        catch (NumberFormatException e) { System.out.println("Невірний формат"); return; }

        LogGenerator.generate(path, count, 42L);
    }

    private static void handleFilter() {
        System.out.print("Вхідний файл: ");
        String src = sc.nextLine().trim();
        System.out.print("Вихідний файл: ");
        String dst = sc.nextLine().trim();

        System.out.println("Мінімальний рівень:");
        System.out.println("  1. CRITICAL");
        System.out.println("  2. ERROR");
        System.out.println("  3. WARNING");
        System.out.println("  4. INFO");
        System.out.println("  5. DEBUG");
        System.out.print("> ");
        String c = sc.nextLine().trim();

        LogLevel level;
        switch (c) {
            case "1": level = LogLevel.CRITICAL; break;
            case "2": level = LogLevel.ERROR;    break;
            case "3": level = LogLevel.WARNING;  break;
            case "4": level = LogLevel.INFO;     break;
            case "5": level = LogLevel.DEBUG;    break;
            default : System.out.println("Невірний вибір"); return;
        }

        filter.filter(src, dst, level);
    }
}