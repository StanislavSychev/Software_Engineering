package ru.ifmo.cli.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.CommandExecutor;
import ru.ifmo.cli.CommandTest;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class CdLsTest extends CommandTest {

    private CommandExecutor commandExecutor;

    @Before
    public void setUp() {
        commandExecutor = new CommandExecutor();

        new File("/tmp/test-cli/1").mkdirs();
        new File("/tmp/test-cli/2").mkdirs();
        new File("/tmp/test-cli/3").mkdirs();
        new File("/tmp/test-cli/4").mkdirs();
    }

    @Test
    public void testFail(){
        assertEquals("asfadfa/wdwd/wd : no such file or directory\n", commandExecutor.execCommand("ls asfadfa/wdwd/wd"));
    }

    @Test
    public void testFunctionality(){
        assertEquals("/private/tmp/test-cli/1\n/private/tmp/test-cli/4\n/private/tmp/test-cli/3\n/private/tmp/test-cli/2\n", commandExecutor.execCommand("ls /tmp/test-cli"));

        commandExecutor.execCommand("cd /tmp/test-cli");

        assertEquals("/private/tmp/test-cli/1\n/private/tmp/test-cli/4\n/private/tmp/test-cli/3\n/private/tmp/test-cli/2\n", commandExecutor.execCommand("ls ../test-cli"));

        commandExecutor.execCommand("cd ../../tmp/test-cli/4/.");

        assertEquals("", commandExecutor.execCommand("ls ."));
    }

    @After
    public void tearDown() {
        (new File("/tmp/test-cli/1")).delete();
        (new File("/tmp/test-cli/2")).delete();
        (new File("/tmp/test-cli/3")).delete();
        (new File("/tmp/test-cli/4")).delete();
    }
}