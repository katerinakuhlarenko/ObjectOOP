package com.example.collections.exceptions;

public class InvalidInitialSizeException extends Exception {
    public InvalidInitialSizeException(String message) { super(message); }
    public InvalidInitialSizeException()               { super("Invalid initial size"); }
}
