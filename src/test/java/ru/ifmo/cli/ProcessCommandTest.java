package ru.ifmo.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessCommandTest extends CommandTest{

    @Test
    public void testProcess() {
        assertEquals("hello world\n", commandExecutor.execCommand("python test.py"));
        assertEquals("command pthon not found\n", commandExecutor.execCommand("pthon test.py"));
    }



}