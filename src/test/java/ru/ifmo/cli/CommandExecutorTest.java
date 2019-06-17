package ru.ifmo.cli;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
public class CommandExecutorTest {

    CommandExecutor commandExecutor;

    @Before
    public void setUp() {
        commandExecutor = new CommandExecutor();
    }

    @Test
    public void testAssign() {
        commandExecutor.execCommand("a=1");
        commandExecutor.execCommand("b='123'");
        assertEquals("1\n", commandExecutor.execCommand("echo $a"));
        assertEquals("123\n", commandExecutor.execCommand("echo $b"));
        assertEquals("empty assignment\n", commandExecutor.execCommand("a="));
        commandExecutor.execCommand("exit");
        assertTrue(commandExecutor.isFinished());
    }


}