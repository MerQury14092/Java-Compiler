package org.example.interfaces;

public interface CommandInterpreter {
    public void run(CommandOutput output, String... args);
    public void interrupt();
}
