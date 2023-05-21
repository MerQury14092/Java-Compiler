package org.example.logic;

import org.example.interfaces.CommandInterpreter;
import org.example.interfaces.CommandOutput;

import java.io.IOException;

public class Compiler implements CommandInterpreter {

    private CommandOutput output;
    private String urlToJar, urlToExe, urlToIco, urlToJson, processName;
    private boolean excludeCli;

    /**
     * args[0] = url to jar
     * args[1] = url to exe
     * args[2] = url to ico
     * args[3] = url to json
     * args[4] = process name
     * args[5] = bool exclude CLI (true|false)
     */
    @Override
    public void run(CommandOutput output, String... args) {
        this.output = output;
        urlToJar = args[0];
        urlToExe = args[1];
        if(args[2].isEmpty())
            urlToIco = null;
        else
            urlToIco = args[2];
        if(args[3].isEmpty())
            urlToJson = null;
        else
            urlToJson = args[3];
        if(args[4].isEmpty())
            processName = null;
        else
            processName = args[4];
        excludeCli = Boolean.parseBoolean(args[5]);
        new Thread(() -> {
            compile();
            output.done();
        }).start();
    }

    private void compile(){
        try {
            Process builder = Runtime.getRuntime().exec(new String[]{
                    "\"%comspec%\"",
                    "/k",
                    String.format("\"\"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\VC\\Auxiliary\\Build\\vcvars64.bat\" && native-image --no-fallback --enable-url-protocols=https -jar %s -Djava.awt.headless=false\"",
                            urlToJar)
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
