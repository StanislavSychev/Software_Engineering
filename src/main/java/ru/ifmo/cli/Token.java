package ru.ifmo.cli;

/**
 * Stores part of command with a type of this part
 */
public class Token {

    public enum TokenType {
        TEXT,
        UNSUBSTITUTED_TEXT,
        ASSIGNMENT,
        PIPE
    }

    private TokenType type;
    private String content;

    public Token(String content, TokenType type) {
        this.content = content;
        this.type = type;
    }

    /**
     * @return Type of token
     */
    public TokenType getType() {
        return type;
    }

    /**
     * @return Content of Token
     */
    public String getContent() {
        return content;
    }

    /**
     * substitute variables in token
     *
     * @param environment Environment to get values from
     */
    public void substitute(Environment environment) {
        if (type != TokenType.UNSUBSTITUTED_TEXT) {
            return;
        }
        int length = content.length();
        StringBuilder newContent = new StringBuilder();
        int i = 0;
        while (i < length) {
            char symbol = content.charAt(i);
            if (symbol == '$') {
                int j = i + 1;
                StringBuilder variableBuilder = new StringBuilder();
                while (j < length && Character.isLetterOrDigit(content.charAt(j))) {
                    variableBuilder.append(content.charAt(j));
                    j++;
                }
                String variable = variableBuilder.toString();

                if (variable.length() == 0) {
                    newContent.append(symbol);
                } else {

                    newContent.append(environment.getValue(variable));
                }
                i = j;
            } else {

                newContent.append(symbol);
                i++;
            }
        }
        content = newContent.toString();
        type = Token.TokenType.TEXT;
        if (content.indexOf('=') != -1) {
            type = Token.TokenType.ASSIGNMENT;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token) {
            return ((Token) obj).getContent().equals(content) && ((Token) obj).getType() == type;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

}
