package ru.ifmo.cli;

public class NoSuchPathException extends RuntimeException {
    private String message;

    public NoSuchPathException(String message)  {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
