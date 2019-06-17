package ru.ifmo.cli;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Starts external process
 */
public class ProcessCommand implements Command {
    /**
     * runs external process
     * @param args        command arguments
     * @param environment environment for command to get or change variables
     * @return string returned by process
     */
    @Override
    public String execute(List<String> args, Environment environment, boolean pipe) {
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process;
        try {
            process = processBuilder.start();

        } catch (IOException e) {
            throw new SyntaxException("command " + args.get(0) + " not found");
        }

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            StackTraceElement[] stack = e.getStackTrace();
            StringBuilder res = new StringBuilder();
            for (StackTraceElement ste : stack) {
                res.append(ste.toString());
            }
            return res.toString();
        }

        InputStream inputStream = process.getInputStream();
        String result = "";
        try (Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
            result = s.hasNext() ? s.next() : "";
        }
        return result;
    }
}
