package org.example.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String messgae) {
        super(messgae);
    }
}
