package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.SyntaxisException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Cat implements Command {
    @Override
    public String execute(List<String> args, Environment environment) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < args.size(); i++) {
            String fileName = args.get(i);
            try {
                res.append(new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new SyntaxisException("No such file: " + fileName);
            }
        }
        return res.toString();
    }
}
