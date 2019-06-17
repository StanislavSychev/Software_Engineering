package ru.ifmo.cli.commands;

import org.junit.Test;
import ru.ifmo.cli.CommandTest;

import static org.junit.Assert.*;

public class CatTest extends CommandTest {

    @Test
    public void testCat() {
        String test = "as \n" +
                "asdasd\n" +
                "asf\n" +
                "adfsda\n" +
                "asdg\n" +
                "asg asasd\n" +
                "sag asgasdga s\n" +
                "asgas\n";
        assertEquals(test, commandExecutor.execCommand("cat test.txt"));
        assertEquals(test + test, commandExecutor.execCommand("cat test.txt test.txt"));
    }

}