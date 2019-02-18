package ru.ifmo.cli.commands;

import ru.ifmo.cli.Environment;
import ru.ifmo.cli.SyntaxisException;

import java.nio.file.Paths;
import java.util.List;

public class Pwd implements Command {
    /**
     * @param args        command arguments (should be empty)
     * @param environment environment for command to get or change variables
     * @return current directory
     */
    @Override
    public String execute(List<String> args, Environment environment) {
        if (!args.isEmpty()) {
            throw new SyntaxisException("too many arguments");
        }
        return Paths.get(".").toAbsolutePath().toString();
    }
}
