package ru.ifmo.cli;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores all current variables
 */
public class Environment {

    private Path currentDirectory = Paths.get(System.getProperty("user.dir"));
    private Map<String, String> variables = new HashMap<>();

    /**
     * @param name name of new variable
     * @param value value of new variable
     */
    public void setValue(String name, String value) {
        variables.put(name, value);
    }

    /**
     * @param name name of variable
     * @return value of variable or empty string
     */
    public String getValue(String name) {
        return variables.getOrDefault(name, "");
    }

    /**
     * @param newDirectory new directory in which interpreter will stay
     */
    public void setCurrentDirectory(Path newDirectory) {
        currentDirectory = newDirectory;
    }

    /**
     * @return absolute path to current directory
     */
    public Path getCurrentDirectory() {
        return currentDirectory;
    }
}
