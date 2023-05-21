package org.example.logic;

import org.example.interfaces.CommandInterpreter;
import org.example.interfaces.CommandOutput;

import java.util.Arrays;

public class Compiler implements CommandInterpreter {

    /**
     * args[0] = url to jar
     * args[1] = url to exe
     * args[2] = url to ico
     * args[3] = url to json
     * args[4] = bool exclude CLI (true|false)
     */
    @Override
    public void run(CommandOutput output, String... args) {
        Arrays.stream(args).forEach(System.out::println);
        output.write("done");
    }
}
