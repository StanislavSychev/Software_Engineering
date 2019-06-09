package ru.ifmo.cli.commands;

import org.junit.Test;
import ru.ifmo.cli.CommandTest;

import static org.junit.Assert.*;

public class PwdTest extends CommandTest {

    @Test
    public void testFail(){
        assertEquals("too many arguments\n", commandExecutor.execCommand("pwd asd"));
    }

}