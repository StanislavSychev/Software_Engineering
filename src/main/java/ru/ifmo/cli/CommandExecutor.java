package ru.ifmo.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import ru.ifmo.cli.commands.*;

/**
 * Executes commands
 */
public class CommandExecutor {

    private Environment environment;
    private boolean finished;
    private ProcessCommand prcessCommand;

    public CommandExecutor() {
        environment = new Environment();
        finished = false;
        prcessCommand = new ProcessCommand();
    }

    private static final Map<String, Command> COMMANDS_LIST = new HashMap<String, Command>() {
        {
            put("echo", new Echo());
            put("pwd", new Pwd());
            put("wc", new Wc());
            put("cat", new Cat());
            put("grep", new Grep());
        }
    };

    private String execute(List<Token> tokens) {
        Token command = tokens.get(0);
        if (command.getType() == Token.TokenType.ASSIGNMENT) {
            int index = command.getContent().indexOf('=');
            if (tokens.size() == 1 && command.getContent().length() - 1 == index) {
                throw new SyntaxisException("empty assignment");
            }
            StringBuilder sb = new StringBuilder();
            for (Token token : tokens) {
                sb.append(token.getContent());
            }
            String fullCommand = sb.toString();
            environment.setValue(fullCommand.substring(0, index), fullCommand.substring(index + 1));
            return null;
        }
        if (command.getContent().equals("exit")) {
            finished = true;
            return null;
        }
        tokens.remove(0);
        List<String> args = new ArrayList<>();
        for (Token token : tokens) {
            args.add(token.getContent());
        }
        if (COMMANDS_LIST.containsKey(command.getContent())) {
            return COMMANDS_LIST.get(command.getContent()).execute(args, environment);
        }
        args.add(0, command.getContent());
        return prcessCommand.execute(args, environment);
    }

    /**
     * @param command command to execute
     * @return result of execution
     */
    public String execCommand(String command) {
        try {
            List<Token> tokens = Tokenizer.tokenize(command, environment);
            Parser parser = new Parser(tokens);
            List<Token> commandTokens = parser.nextCommand();
            String res = "";
            while (!commandTokens.isEmpty() && !finished) {
                res = execute(commandTokens);
                commandTokens = parser.nextCommand();
                if (!commandTokens.isEmpty() && res != null) {
                    commandTokens.add(new Token(res, Token.TokenType.TEXT));
                }
            }
            if (finished || res == null) return "\n";
            return res;
        } catch (SyntaxisException e) {
            return e.getMessage() + "\n";
        }
    }

    /**
     * @return true if "exit" command was executed, false otherwise
     */
    public boolean isFinished() {
        return finished;
    }
}
