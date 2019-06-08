package ru.ifmo.cli.commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.ifmo.cli.Command;
import ru.ifmo.cli.Environment;
import ru.ifmo.cli.Parser;
import ru.ifmo.cli.SyntaxisException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep implements Command {

    @Parameter(names = "-i")
    private boolean caseInsensitive = false;

    @Parameter(names = "-A")
    private int linesNumber = -1;

    @Parameter(names = "-w")
    private boolean wholeWord = false;

    @Parameter
    private List<String> parameters = new LinkedList<>();

    @Override
    public String execute(List<String> args, Environment environment) {
        String[] argsArray = new String[args.size()];
        args.toArray(argsArray);
        wholeWord = false;
        linesNumber = -1;
        caseInsensitive = false;
        parameters.clear();
        new JCommander(this, argsArray);

        String regexp = parameters.get(0);

        if (wholeWord) {
            regexp = "( |^)" + regexp + "( |$)";
        }
        Pattern pattern;
        if (caseInsensitive) {
            pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(regexp);
        }
        if (parameters.size() < 2) {
            throw new SyntaxisException("no file was specified");
        }
        StringBuilder res = new StringBuilder();
        for (String fileName : parameters.subList(1, parameters.size())) {
            String file;
            try {
                file = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new SyntaxisException("No such file: " + fileName);
            }
            int linesPrintedAfter = linesNumber;
            boolean toPrint;
            for (String line : file.split("\n")) {
                toPrint = (linesPrintedAfter < linesNumber);
                Matcher matcher = pattern.matcher(line);
                boolean found = matcher.find();
                if (found || toPrint) {
                    res.append(line);
                    res.append("\n");
                    if (found) {
                        linesPrintedAfter = 0;
                    } else {
                        linesPrintedAfter++;
                    }
                }
            }
        }
        return res.toString();
    }
}
