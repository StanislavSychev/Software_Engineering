package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.SyntaxException;

import java.nio.file.Paths;
import java.util.List;

/**
 * pwd command
 */
public class Pwd implements Command {
    /**
     * returns current dir
     * @param args        command arguments (should be empty)
     * @param environment environment for command to get or change variables
     * @return current directory
     */
    @Override
    public String execute(List<String> args, Environment environment, boolean pipe) {
        if (!args.isEmpty()) {
            throw new SyntaxException("too many arguments");
        }
        return Paths.get(".").toAbsolutePath().toString() + "\n";
    }
}
