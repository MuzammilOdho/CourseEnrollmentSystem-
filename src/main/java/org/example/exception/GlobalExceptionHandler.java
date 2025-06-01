package org.example.exception;

public class GlobalExceptionHandler {

    public static void handle(Exception e) {
        System.err.println("[ERROR] " + e.getMessage());
    }
}
