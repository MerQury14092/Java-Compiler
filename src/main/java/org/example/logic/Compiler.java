package org.example.logic;

import org.example.interfaces.CommandInterpreter;
import org.example.interfaces.CommandOutput;

import java.io.*;

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
        // Установите путь к vcvars64.bat файлу
        String vcvarsPath = "C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\VC\\Auxiliary\\Build\\vcvars64.bat";

        // Установите путь к jar-файлу
        String jarPath = "C:\\path\\to\\your\\jar\\file.jar";

        try {
            ProcessBuilder builder = new ProcessBuilder();

            builder.directory(new File("C:\\Users\\MerQury\\Desktop\\asd"));

            System.out.println(urlToJar);

            builder.command("cmd.exe", "/c", "call", vcvarsPath, "&&", "native-image", "--no-fallback",
                    "--enable-url-protocols=https", "-jar", urlToJar, "-Djava.awt.headless=false");

            Process process = builder.start();

            int exitCode = process.waitFor();

            InputStream in = process.getInputStream();

            System.out.write(in.readAllBytes());
            System.out.flush();

            System.out.println("Код завершения: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
