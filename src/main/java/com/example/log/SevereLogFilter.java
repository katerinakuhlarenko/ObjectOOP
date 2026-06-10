package com.example.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SevereLogFilter implements LogFilter {

    @Override
    public void filter(String source_file, String target_file, LogLevel level) {
        long totalLines = 0;
        long matched    = 0;

        try (
                FileReader     fr      = new FileReader(source_file);
                BufferedReader br      = new BufferedReader(fr);
                Scanner        scanner = new Scanner(br);
                FileWriter     fw      = new FileWriter(target_file);
                BufferedWriter bw      = new BufferedWriter(fw);
                PrintWriter    pw      = new PrintWriter(bw)
        ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                totalLines++;

                LogLevel lineLevel = extractLevel(line);
                if (lineLevel != null && lineLevel.atLeast(level)) {
                    pw.println(line);
                    matched++;
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка обробки файлу: " + e.getMessage());
            return;
        }

        System.out.println("Оброблено рядків: " + totalLines);
        System.out.println("Збережено: " + matched + " (рівень " + level.getTag() + " та вище)");
    }

    private LogLevel extractLevel(String line) {
        for (LogLevel l : LogLevel.values()) {
            if (line.contains(l.getBracketedTag())) return l;
        }
        return null;
    }
}