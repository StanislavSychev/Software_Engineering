package ru.ifmo.cli.commands;

import ru.ifmo.cli.Environment;

import java.util.List;

public class Echo implements Command {

    @Override
    public String execute(List<String> args, Environment environment) {
        StringBuilder res = new StringBuilder();
        for (String arg : args) {
            res.append(arg);
        }
        return res.toString();
    }
}
