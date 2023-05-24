package org.example.logic;

import org.example.interfaces.CommandInterpreter;
import org.example.interfaces.CommandOutput;

import java.io.*;
import java.util.Scanner;

public class Compiler implements CommandInterpreter {

    private CommandOutput output;
    private String urlToJar, urlToExe, urlToIco, urlToJson, processName;
    private Process process;
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
        if (args[2].isEmpty())
            urlToIco = null;
        else
            urlToIco = args[2];
        if (args[3].isEmpty())
            urlToJson = null;
        else
            urlToJson = args[3];
        if (args[4].isEmpty())
            processName = null;
        else
            processName = args[4];
        excludeCli = Boolean.parseBoolean(args[5]);
        new Thread(() -> {
            compile();
            output.done();
        }).start();
    }

    @Override
    public void interrupt() {
        // TODO: 24.05.2023 CREATE INTERRUPT PROCESS COMPILATION
    }

    private void compile() {
        // Установите путь к vcvars64.bat файлу
        String vcvarsPath = "\"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\VC\\Auxiliary\\Build\\vcvars64.bat\"";

        try {
            ProcessBuilder builder = new ProcessBuilder();

            builder.command("cmd", "/c", String.format("\"%s && native-image %s%s%s-H:Path=%s -H:Name=%s --no-fallback --enable-url-protocols=https -jar %s -Djava.awt.headless=false%s\"",
                    vcvarsPath,
                    /*urlToIco == null ? "" : "--icon=\"" + urlToIco + "\" "*/"",
                    urlToJson == null ? "" : "-H:ReflectionConfigurationFiles=\"" + urlToJson + "\" ",
                    /*processName == null ? "" : "--name=\"" + processName + "\" "*/"",
                    "\"" + strArrToStr(urlToExe.split("\\\\"), urlToExe.split("\\\\").length - 2) + "\"",
                    "\"" + urlToExe.split("\\\\")[urlToExe.split("\\\\").length - 1].replace(".exe", "") + "\"",
                    "\"" + urlToJar + "\"",
                    excludeCli ? " && editbin /SUBSYSTEM:WINDOWS " + urlToExe : ""));


            process = builder.start();
            new Thread(() -> {
                Scanner sc = new Scanner(process.getInputStream());
                while (process.isAlive()) {
                    if (sc.hasNextLine()) {
                        String str = sc.nextLine();
                        System.out.println(str);
                        handleLine(str, output);
                    }
                }
            }).start();
            process.waitFor();
            if (process.exitValue() != 0) {
                InputStream inEr = process.getErrorStream();
                output.write("Compilation error");
                System.out.println("Произошла ошикба:");
                System.out.println(new String(inEr.readAllBytes(), "cp866"));
            }
            process.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Обрезает массив до end, включая и клеит всё в одну строку, включая между элементами разделитель \
    private String strArrToStr(String[] arr, int end) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= end; i++) {
            res.append(arr[i]).append(i != end ? '\\' : "");
        }
        return res.toString();
    }

    private void handleLine(String line, CommandOutput output) {
        if (line.contains("[1/7] Initializing..."))
            output.write("[1/7] Initializing...");
        else if (line.contains("GraalVM Native Image: Generating")) {
            output.write("Compilation started");
        } else if (line.contains("[2/7] Performing analysis...")) {
            output.write("[2/7] Performing analysis...");
        } else if (line.contains("[3/7] Building universe...")) {
            output.write("[3/7] Building universe...");
        } else if (line.contains("[4/7] Parsing methods...")) {
            output.write("[4/7] Parsing methods...");
        } else if (line.contains("[5/7] Inlining methods...")) {
            output.write("[5/7] Inlining methods...");
        } else if (line.contains("[6/7] Compiling methods...")) {
            output.write("[6/7] Compiling methods...");
        } else if (line.contains("[7/7] Creating image...")) {
            output.write("[7/7] Creating image...");
        } else if (line.contains("Finished generating")) {
            output.write("Compilation finished");
        }
    }
}
