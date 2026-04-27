package com.example.collections.exceptions;

public class ListCapacityLimitException extends Exception {
    public ListCapacityLimitException(String message) { super("Capacity limit reached: " + message); }
    public ListCapacityLimitException()               { super("Capacity limit reached"); }
}
