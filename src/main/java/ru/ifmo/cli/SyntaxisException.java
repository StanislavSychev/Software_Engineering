package ru.ifmo.cli;

public class SyntaxisException extends RuntimeException {
    private String message;

    public SyntaxisException(String message)  {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
