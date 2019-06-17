package ru.ifmo.cli;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores all current variables
 */
public class Environment {

    private Map<String, String> variables = new HashMap<>();

    /**
     * Set variable value
     * @param name name of new variable
     * @param value value of new variable
     */
    public void setValue(String name, String value) {
        variables.put(name, value);
    }

    /**
     * get variable value
     * @param name name of variable
     * @return value of variable or empty string
     */
    public String getValue(String name) {
        return variables.getOrDefault(name, "");
    }
}
