package ru.ifmo.cli.commands;

import org.junit.Test;
import ru.ifmo.cli.CommandTest;

import static org.junit.Assert.*;

public class EchoTest extends CommandTest {

    @Test
    public void testEcho() {
        assertEquals("123\n", commandExecutor.execCommand("echo 123"));
        assertEquals("123abc\n", commandExecutor.execCommand("echo 123 'abc'"));
        assertEquals("123 abc\n", commandExecutor.execCommand("echo \"123 abc\""));
        assertEquals("\n", commandExecutor.execCommand("echo"));
    }

}