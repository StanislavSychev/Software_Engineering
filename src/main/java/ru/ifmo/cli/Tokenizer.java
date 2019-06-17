package ru.ifmo.cli;

import java.util.ArrayList;
import java.util.List;

/**
 * Tokenize String
 */
public class Tokenizer {

    /**
     * Splits line into tokens
     * @param input String to tokenize
     * @param environment Environment with variables value
     * @return List of tokens generated from input string
     */
    public static List<Token> tokenize(String input, Environment environment) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean wasAssignment = false;
        boolean wasSubstitution = false;
        int length = input.length();
        int i = 0;
        while (i < length) {
            char symbol = input.charAt(i);
            currentToken = new StringBuilder();
            currentToken.append(symbol);
            if (symbol == ' ') {
                i++;
                continue;
            }
            if (symbol == '\'' || symbol == '"') {
                currentToken.deleteCharAt(0);
                int num = -1;
                for (int j = i + 1; j < length; j++) {
                    if (input.charAt(j) == symbol) {
                        num = j;
                        break;
                    }
                    currentToken.append(input.charAt(j));
                }
                if (num >= 0) {
                    Token.TokenType newType = Token.TokenType.TEXT;
                    if (symbol == '"') newType = Token.TokenType.UNSUBSTITUTED_TEXT;
                    Token newToken = new Token(currentToken.toString(), newType);
                    tokens.add(newToken);
                    currentToken = new StringBuilder();
                    i = num + 1;
                    continue;
                } else {
                    throw new SyntaxException("No pair for a quote found");
                }
            }
            if (symbol == '|') {
                tokens.add(new Token(currentToken.toString(), Token.TokenType.PIPE));
                i++;
                continue;
            }
            wasAssignment = false;
            wasSubstitution = false;
            if (symbol == '=') {
                wasAssignment = true;
            }
            if (symbol == '$') {
                wasSubstitution = true;
            }
            boolean wasStopped = false;
            for (int j = i + 1; j < length; j++) {
                char currentSymbol = input.charAt(j);
                if (" \"'|".contains(String.valueOf(currentSymbol))) {
                    Token.TokenType currentType = Token.TokenType.TEXT;
                    if (wasAssignment) currentType = Token.TokenType.ASSIGNMENT;
                    if (wasSubstitution) currentType = Token.TokenType.UNSUBSTITUTED_TEXT;
                    tokens.add(new Token(currentToken.toString(), currentType));
                    currentToken = new StringBuilder();
                    i = j;
                    wasStopped = true;
                    break;
                } else {
                    currentToken.append(currentSymbol);
                    if (currentSymbol == '=') {
                        wasAssignment = true;
                    }
                    if (currentSymbol == '$') {
                        wasSubstitution = true;
                    }
                }
            }
            if (!wasStopped) {
                i = length;
            }
        }
        if (currentToken.length() > 0) {
            Token.TokenType currentType = Token.TokenType.TEXT;
            if (wasAssignment) currentType = Token.TokenType.ASSIGNMENT;
            if (wasSubstitution) currentType = Token.TokenType.UNSUBSTITUTED_TEXT;
            tokens.add(new Token(currentToken.toString() ,currentType));
        }
        for (Token token : tokens) {
            token.substitute(environment);
        }
        return tokens;
    }
}
