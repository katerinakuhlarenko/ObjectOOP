package com.example.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class LogGenerator {

    private static final String[] MODULES = {
            "AuthModule", "DBConnector", "Network", "FileSystem",
            "Scheduler", "Renderer", "Cache", "Validator"
    };

    private static final String[] MESSAGES = {
            "Operation completed successfully",
            "Connection established",
            "Request timeout after 30s",
            "User session expired",
            "Cache miss for key 'session-abc'",
            "Failed to allocate buffer",
            "Database connection pool exhausted",
            "Out of memory error",
            "Configuration loaded from /etc/app.conf",
            "Heartbeat received from node-7"
    };

    public static void generate(String filename, int lineCount, long seed) throws IOException {
        Random rng = new Random(seed);
        LogLevel[] levels = LogLevel.values();
        LocalDateTime base = LocalDateTime.of(2026, 6, 10, 0, 0, 0);
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        try (
                FileWriter     fw = new FileWriter(filename);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter    pw = new PrintWriter(bw)
        ) {
            for (int i = 1; i <= lineCount; i++) {
                LogLevel level = levels[rng.nextInt(levels.length)];
                String module = MODULES[rng.nextInt(MODULES.length)];
                String msg = MESSAGES[rng.nextInt(MESSAGES.length)];
                LocalDateTime ts = base.plusSeconds(i * 7L);

                pw.printf("%08d %sZ %s %s %s%n",
                        i,
                        ts.format(fmt),
                        level.getBracketedTag(),
                        module,
                        msg);
            }
        }
        System.out.println("Згенеровано " + lineCount + " рядків у файл: " + filename);
    }
}