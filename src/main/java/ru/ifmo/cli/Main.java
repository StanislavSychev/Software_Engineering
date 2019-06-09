package ru.ifmo.cli;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        CommandExecutor ce = new CommandExecutor();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String startLine = "~$ ";
        while (!ce.isFinished()) {
            System.out.print(startLine);
            String command = bufferedReader.readLine();
            System.out.print(ce.execCommand(command));
        }

    }
}
