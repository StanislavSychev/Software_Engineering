package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.NoSuchPathException;
import ru.ifmo.cli.SyntaxisException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Cd implements Command {

    @Override
    public String execute(List<String> args, Environment environment) {
        if (args.size() > 1) {
            throw new SyntaxisException("too many arguments");
        }

        if (args.size() == 0) {
            environment.setCurrentDirectory(Paths.get(System.getProperty("user.home")));
            return "";
        }

        String command = args.get(0);

        try {
            environment.setCurrentDirectory((environment.getCurrentDirectory().resolve(command)).toAbsolutePath().toRealPath());
        } catch (IOException e) {
            throw new NoSuchPathException(command + " : no such file or directory");
        }

        return "";
    }
}
