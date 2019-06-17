package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.SyntaxisException;

import java.util.List;

public class Assign implements Command {
    @Override
    public String execute(List<String> args, Environment environment) {
        int index = args.get(0).indexOf('=');
        if (args.size() == 1 && args.get(0).length() - 1 == index) {
            throw new SyntaxisException("empty assignment");
        }
        String fullCommand = String.join("", args);
        environment.setValue(fullCommand.substring(0, index), fullCommand.substring(index + 1));
        return null;
    }
}
