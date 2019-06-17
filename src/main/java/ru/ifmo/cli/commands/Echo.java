package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Echo command
 */
public class Echo implements Command {

    /**
     * returnees given arguments
     * @param args        command arguments
     * @param environment environment for command to get or change variables
     * @return arguments joined by space
     */
    @Override
    public String execute(List<String> args, Environment environment, boolean pipe) {
        return args.stream().collect(Collectors.joining(" ", "", "\n"));
    }
}
