package ru.ifmo.cli;

import org.junit.Before;
import ru.ifmo.cli.CommandExecutor;

import static org.junit.Assert.*;

public class CommandTest {

    protected CommandExecutor commandExecutor;

    @Before
    public void setUp() {
        commandExecutor = new CommandExecutor();
    }

}