package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;

import java.util.List;
import java.util.stream.Collectors;

public class Echo implements Command {

    @Override
    public String execute(List<String> args, Environment environment) {
        return args.stream().collect(Collectors.joining(" ", "", "\n"));
    }
}
