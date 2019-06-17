package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.SyntaxException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Cat command
 */
public class Cat implements Command {
    /**
     * Returns file content
     * @param args        command arguments
     * @param environment environment for command to get or change variables
     * @return file as string
     */
    @Override
    public String execute(List<String> args, Environment environment, boolean pipe) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < args.size(); i++) {
            String fileName = args.get(i);
            try {
                res.append(new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8));
            } catch (IOException | InvalidPathException e) {
                if (pipe) {
                    return fileName;
                }
                throw new SyntaxException("No such file: " + fileName);
            }
        }
        return res.toString();
    }
}
