package ru.ifmo.cli;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import ru.ifmo.cli.commands.*;

/**
 * Executes commands
 */
public class CommandExecutor {

    private final Environment environment = new Environment();
    private boolean finished = false;
    private ProcessCommand prcessCommand = new ProcessCommand();

    private final Map<String, Command> COMMANDS_LIST = new HashMap<String, Command>() {
        {
            put("echo", new Echo());
            put("pwd", new Pwd());
            put("wc", new Wc());
            put("cat", new Cat());
            put("exit", new Exit());
            put("assign", new Assign());
        }
    };

    private class Exit implements Command{

        @Override
        public String execute(List<String> args, Environment environment) {
            finished = true;
            return null;
        }
    }

    /**
     * executes parsed command
     * @param tokens parsed command
     * @return command result
     */
    private String execute(List<Token> tokens) {
        Token command = tokens.remove(0);
        List<String> args = tokens
                .stream()
                .map(Token::getContent)
                .collect(Collectors.toList());
        if (command.getType() == Token.TokenType.ASSIGNMENT) {
            args.add(0, command.getContent());
            command = new Token("assign", Token.TokenType.TEXT);
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
        } catch (SyntaxException e) {
            return e.getMessage() + "\n";
        }
    }

    /**
     * Check if exit command was typed
     * @return true if "exit" command was executed, false otherwise
     */
    public boolean isFinished() {
        return finished;
    }
}
