package ru.ifmo.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        CommandExecutor ce = new CommandExecutor();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String startLine = "~$ ";
        while (!ce.isFinished()) {
            //System.out.print(startLine);
            System.out.println(ce.execCommand(bufferedReader.readLine()));
        }

    }
}
