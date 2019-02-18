package ru.ifmo.cli.commands;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.CommandExecutor;
import ru.ifmo.cli.SyntaxisException;

import static org.junit.Assert.*;

public class PwdTest extends CommandTest {

    @Test
    public void testFail(){
        assertEquals("too many arguments", commandExecutor.execCommand("pwd asd"));
    }

}