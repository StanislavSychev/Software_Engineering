package ru.ifmo.cli.commands;

import ru.ifmo.cli.Environment;

import java.util.List;

public interface Command {
    String execute(List<String> args, Environment environment);
}
