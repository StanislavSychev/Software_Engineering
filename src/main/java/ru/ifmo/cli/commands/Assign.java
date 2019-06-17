package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.SyntaxException;

import java.util.List;

/**
 * Assigns variables in environment
 */
public class Assign implements Command {
    /**
     * assignees variable, returns null
     * @param args        command arguments
     * @param environment environment for command to get or change variables
     * @return null
     */
    @Override
    public String execute(List<String> args, Environment environment) {
        int index = args.get(0).indexOf('=');
        if (args.size() == 1 && args.get(0).length() - 1 == index) {
            throw new SyntaxException("empty assignment");
        }
        String fullCommand = String.join("", args);
        environment.setValue(fullCommand.substring(0, index), fullCommand.substring(index + 1));
        return null;
    }
}
