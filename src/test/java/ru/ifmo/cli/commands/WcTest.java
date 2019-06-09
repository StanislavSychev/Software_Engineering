package ru.ifmo.cli.commands;

import org.junit.Test;
import ru.ifmo.cli.CommandTest;

import static org.junit.Assert.*;

public class WcTest extends CommandTest {

    @Test
    public void testWc() {
        assertEquals("8 11 58\n", commandExecutor.execCommand("wc test.txt"));
        String got = commandExecutor.execCommand("wc tst.txt");
        assertEquals("test.txt: 8 11 58\ntest.txt: 8 11 58\n", commandExecutor.execCommand("wc test.txt test.txt"));
        assertEquals("1 2 7\n", commandExecutor.execCommand("wc tst.txt"));
        assertEquals("1 2 7\n", commandExecutor.execCommand("wc 'avs 123'"));
    }

}