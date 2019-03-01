package ru.ifmo.cli.commands;

import org.junit.Test;
import ru.ifmo.cli.CommandTest;

import static org.junit.Assert.*;

public class GrepTest extends CommandTest {

    @Test
    public void testGrep() {
        String get = commandExecutor.execCommand("grep \"f\" test.txt");
        assertEquals("as \n", commandExecutor.execCommand("grep -w \"as\" test.txt"));
        assertEquals("asf\nadfsda\n", commandExecutor.execCommand("grep \"f\" test.txt"));
        assertEquals("asf\nadfsda\nasdg\n", commandExecutor.execCommand("grep -A 1 \"f\" test.txt"));
        assertEquals("asf\nadfsda\n", commandExecutor.execCommand("grep -i \"F\" test.txt"));
        assertEquals("as \n", commandExecutor.execCommand("grep -wi \"AS\" test.txt"));
    }

}