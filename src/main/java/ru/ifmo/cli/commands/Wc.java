package ru.ifmo.cli.commands;

import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * wc command
 */
public class Wc implements Command {
    /**
     * Counts bytes, words and lines in files or given string
     * @param args        command arguments
     * @param environment environment for command to get or change variables
     * @return
     */
    @Override
    public String execute(List<String> args, Environment environment) {
        if (args.size() == 1) {
            try {
                return count(new String(Files.readAllBytes(Paths.get(args.get(0))), StandardCharsets.UTF_8));
            } catch (IOException e) {
                return count(args.get(0));
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < args.size(); i++) {
            String fileName = args.get(i);
            try {
                res.append(fileName);
                res.append(": ");
                res.append(count(new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8)));
            } catch (IOException e) {
                res = new StringBuilder();
                for (String part : args) {
                    res.append(part);
                }
                return count(res.toString());
            }
        }
        return res.toString();
    }

    /**
     * counts lines, words and bytes in string
     * @param toCount string to get count
     * @return counts as string joined by space
     */
    private String count(String toCount) {
        int lines = toCount.split("\n").length;
        int words = toCount.split("\\W+").length;
        int bytes = toCount.length();
        return String.valueOf(lines) + " " + String.valueOf(words) + " " + String.valueOf(bytes) + "\n";
    }
}
