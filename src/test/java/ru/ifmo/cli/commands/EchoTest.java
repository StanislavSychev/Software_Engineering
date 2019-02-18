package ru.ifmo.cli.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class EchoTest extends CommandTest{

    @Test
    public void testEcho() {
        assertEquals("123", commandExecutor.execCommand("echo 123"));
        assertEquals("123abc", commandExecutor.execCommand("echo 123 'abc'"));
        assertEquals("123 abc", commandExecutor.execCommand("echo \"123 abc\""));
        assertEquals("", commandExecutor.execCommand("echo"));
    }

}