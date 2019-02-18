package ru.ifmo.cli;

import ru.ifmo.cli.Environment;

import java.util.List;

public interface Command {
    /**
     * @param args command arguments
     * @param environment environment for command to get or change variables
     * @return result of command execution
     */
    String execute(List<String> args, Environment environment);
}
