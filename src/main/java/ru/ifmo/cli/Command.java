package ru.ifmo.cli;

import ru.ifmo.cli.Environment;

import java.util.List;

/**
 * Command interface
 * All CLI commands should implement this interface
 */
public interface Command {
    /**
     * returns result of command execution as string
     * @param args command arguments
     * @param environment environment for command to get or change variables
     * @return result of command execution
     */
    String execute(List<String> args, Environment environment, boolean pipe);
}
