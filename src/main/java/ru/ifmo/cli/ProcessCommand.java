package ru.ifmo.cli;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class ProcessCommand implements Command {
    @Override
    public String execute(List<String> args, Environment environment) {
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process;
        try {
            process = processBuilder.start();

        } catch (IOException e) {
            throw new SyntaxisException("command " + args.get(0) + " not found");
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
        try(Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
            result = s.hasNext() ? s.next() : "";
        }
        return result;
    }
}
