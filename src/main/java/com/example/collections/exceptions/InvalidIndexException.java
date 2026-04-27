package com.example.collections.exceptions;

public class InvalidIndexException extends RuntimeException {
    public InvalidIndexException(String message) { super(message); }
    public InvalidIndexException()               { super("Invalid index"); }

    @Override
    public String getMessage() { return "Index out of bounds: " + super.getMessage(); }
}
