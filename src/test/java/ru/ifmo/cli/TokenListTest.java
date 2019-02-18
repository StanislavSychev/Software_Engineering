package ru.ifmo.cli;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TokenListTest {

    public void checkEquals(List<Token> actual, List<Token> expected) {
        assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i), expected.get(i));
        }
    }

}
