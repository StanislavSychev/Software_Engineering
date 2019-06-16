package ru.ifmo.cli.commands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.CommandExecutor;
import ru.ifmo.cli.CommandTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CdLsTest extends CommandTest {

    private CommandExecutor commandExecutor;

    private Path tmpCLIPath = Paths.get("tmp", "test-cli");

    @Before
    public void setUp() {
        commandExecutor = new CommandExecutor();

        new File(Paths.get(tmpCLIPath.toString(), "1").toString()).mkdirs();
        new File(Paths.get(tmpCLIPath.toString(), "2").toString()).mkdirs();
        new File(Paths.get(tmpCLIPath.toString(), "3").toString()).mkdirs();
        new File(Paths.get(tmpCLIPath.toString(), "4").toString()).mkdirs();
    }

    @Test
    public void testFail() {
        String badPath = Paths.get("asfadfa", "wdwd", "wd").toString();
        assertEquals(badPath + " : no such file or directory\n", commandExecutor.execCommand("ls " + badPath));
    }

    @Test
    public void testFunctionality() {
        List<String> names = Arrays.asList(Paths.get(tmpCLIPath.toString(), "1").toString(),
                Paths.get(tmpCLIPath.toString(), "2").toString(),
                Paths.get(tmpCLIPath.toString(), "3").toString(),
                Paths.get(tmpCLIPath.toString(), "4").toString());

        names.forEach(x -> assertTrue(commandExecutor.execCommand("ls " + tmpCLIPath).contains(x)));

        commandExecutor.execCommand("cd " + tmpCLIPath);
        names.forEach(x -> assertTrue(commandExecutor.execCommand("ls " + Paths.get("..", "test-cli")).contains(x)));

        commandExecutor.execCommand("cd " + Paths.get("..", "..", "tmp", "test-cli", "4", "."));
        assertEquals("", commandExecutor.execCommand("ls ."));
    }

    @After
    public void tearDown() {
        (new File(Paths.get("tmp").toString())).delete();
    }
}