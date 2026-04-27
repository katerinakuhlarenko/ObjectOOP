package com.example.collections.exceptions;

public class NegativeCapacityException extends RuntimeException {
    public NegativeCapacityException(String message) { super(message); }
    public NegativeCapacityException()               { super("Capacity cannot be negative"); }
}
