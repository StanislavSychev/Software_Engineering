package ru.ifmo.cli;

/**
 * Exception in CLI syntax
 * thrown when unknown command or command parameters is wrong
 */
public class SyntaxException extends RuntimeException {
    private String message;

    public SyntaxException(String message)  {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
