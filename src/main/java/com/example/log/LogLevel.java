package com.example.log;

public enum LogLevel {
    CRITICAL(5, "CRITICAL"),
    ERROR(4, "ERROR"),
    WARNING(3, "WARNING"),
    INFO(2, "INFO"),
    DEBUG(1, "DEBUG");

    private final int severity;
    private final String tag;

    LogLevel(int severity, String tag) {
        this.severity = severity;
        this.tag = tag;
    }

    public int getSeverity()         { return severity; }
    public String getTag()           { return tag; }
    public String getBracketedTag()  { return "[" + tag + "]"; }

    public boolean atLeast(LogLevel other) {
        return this.severity >= other.severity;
    }

    public static LogLevel fromBracketedTag(String token) {
        for (LogLevel l : values()) {
            if (l.getBracketedTag().equals(token)) return l;
        }
        return null;
    }
}