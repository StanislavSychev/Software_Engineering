package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;

import java.util.List;

public class Echo implements Command {

    @Override
    public String execute(List<String> args, Environment environment) {
        StringBuilder res = new StringBuilder();
        for (String arg : args) {
            res.append(arg);
        }
        res.append("\n");
        return res.toString();
    }
}
