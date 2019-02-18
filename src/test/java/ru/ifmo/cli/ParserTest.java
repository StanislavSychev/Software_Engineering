package ru.ifmo.cli;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParserTest extends TokenListTest {

    @Test(expected = SyntaxisException.class)
    public void testFail() {
        List<Token> failPipe = Arrays.asList(
                new Token("|", Token.TokenType.PIPE),
                new Token("a=12", Token.TokenType.ASSIGNMENT)
        );
        Parser parser = new Parser(failPipe);
        parser.nextCommand();
    }

    @Test
    public void testNextCommand() {
        List<Token> empty = Collections.emptyList();
        Token pipe = new Token("|", Token.TokenType.PIPE);
        List<Token> command1 = Arrays.asList(
                new Token("a=12", Token.TokenType.ASSIGNMENT)
        );
        List<Token> command2 = Arrays.asList(
                new Token("a=", Token.TokenType.ASSIGNMENT),
                new Token("abc", Token.TokenType.TEXT)
        );
        List<Token> command3 = Arrays.asList(
                new Token("echo", Token.TokenType.TEXT),
                new Token("1", Token.TokenType.TEXT),
                new Token("2", Token.TokenType.TEXT)
        );
        List<Token> test = new ArrayList<>(command1);
        test.add(pipe);
        test.addAll(command2);
        test.add(pipe);
        test.addAll(command3);
        Parser parser = new Parser(test);
        checkEquals(command1, parser.nextCommand());
        checkEquals(command2, parser.nextCommand());
        checkEquals(command3, parser.nextCommand());
        checkEquals(empty, parser.nextCommand());
    }

}