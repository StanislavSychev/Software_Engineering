package ru.ifmo.cli;

import java.util.ArrayList;
import java.util.List;

/**
 * parse token list into separate commands
 */
public class Parser {

    private List<Token> tokens;
    private int position;
    private int length;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        position = 0;
        length = tokens.size();
    }

    /**
     * @return List of tokens with next command
     */
    public List<Token> nextCommand() {
        List<Token> res = new ArrayList<>();
        while (position < length) {
            Token token = tokens.get(position);
            if (token.getType() == Token.TokenType.PIPE) {
                if (res.isEmpty()) {
                    throw new SyntaxisException("Pipe in the beginning found");
                }
                position++;
                break;
            }
            res.add(token);
            position++;
        }
        return res;
    }
}
