package ru.ifmo.cli;

/**
 * Exception that is thrown when interpreter can't find given @Path.
 */
public class NoSuchPathException extends RuntimeException {
    private String message;

    public NoSuchPathException(String message)  {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
