package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.NoSuchPathException;
import ru.ifmo.cli.SyntaxisException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Ls implements Command {
    @Override
    public String execute(List<String> args, Environment environment) {
        if (args.size() > 1) {
            throw new SyntaxisException("too many arguments");
        }

        Path directory;

        if (args.size() == 0) {
            directory = environment.getCurrentDirectory();
        } else {
            try {
                directory = environment.getCurrentDirectory().resolve(Paths.get(args.get(0))).toAbsolutePath().toRealPath();
            } catch (IOException e) {
                throw new NoSuchPathException(args.get(0) + " : no such file or directory");
            }
        }

        List<Path> listOfFiles;
        try {
            listOfFiles = Files.list(directory).collect(toList());
        } catch (IOException e) {
            throw new NoSuchPathException(directory + " : no such file or directory");
        }

        StringBuilder result = new StringBuilder();

        for (Path path: listOfFiles) {
            result.append(path.toAbsolutePath()).append("\n");
        }

        return result.toString();
    }
}
