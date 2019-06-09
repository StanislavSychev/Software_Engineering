package ru.ifmo.cli;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TokenizerTest extends TokenListTest {

    private static Map<String, List<Token>> tests;
    private static Environment environment;

    @Before
    public void setUp() {
        tests = new HashMap<>();
        environment = new Environment();
        environment.setValue("x", "10");
        String test1 = "echo 123 ' 123' | wc | a=12";
        List<Token> res1 = Arrays.asList(
                new Token("echo", Token.TokenType.TEXT),
                new Token("123", Token.TokenType.TEXT),
                new Token(" 123", Token.TokenType.TEXT),
                new Token("|", Token.TokenType.PIPE),
                new Token("wc", Token.TokenType.TEXT),
                new Token("|", Token.TokenType.PIPE),
                new Token("a=12", Token.TokenType.ASSIGNMENT)
        );
        tests.put(test1, res1);
        String test2 = "b=1|echo \"$x\"";
        List<Token> res2 = Arrays.asList(
                new Token("b=1", Token.TokenType.ASSIGNMENT),
                new Token("|", Token.TokenType.PIPE),
                new Token("echo", Token.TokenType.TEXT),
                new Token("10", Token.TokenType.TEXT)
        );
        tests.put(test2, res2);
        String test3 = "echo $y";
        List<Token> res3 = Arrays.asList(
                new Token("echo", Token.TokenType.TEXT),
                new Token("", Token.TokenType.TEXT)
        );
        tests.put(test3, res3);
    }

    @Test
    public void testTokenize() {
        for(String test : tests.keySet()) {
            checkEquals(Tokenizer.tokenize(test, environment), tests.get(test));
        }
        assertTrue(Tokenizer.tokenize("", environment).isEmpty());
    }

}