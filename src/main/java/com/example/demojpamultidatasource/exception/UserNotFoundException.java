package com.example.demojpamultidatasource.exception;

public class UserNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }
}
