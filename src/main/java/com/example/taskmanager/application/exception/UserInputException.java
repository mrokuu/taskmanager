package com.example.taskmanager.application.exception;

public class UserInputException extends Exception {

    public UserInputException() {
        super();
    }

    public UserInputException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInputException(String message) {
        super(message);
    }

    public UserInputException(Throwable cause) {
        super(cause);
    }
}
