package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

public class Menu extends JFrame {
    public Menu(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        GridBagLayout grid = new GridBagLayout();
        setLayout(grid);
        int dotSize = 20;
        grid.columnWidths = IntStream.generate(() -> dotSize).limit(16).toArray();
        grid.rowHeights = IntStream.generate(() -> dotSize).limit(22).toArray();
        setTitle("JCompiler");

        JFileChooser fileChooser = new JFileChooser();

        JLabel title = new JLabel("Java Compiler");
        JLabel author = new JLabel("Created by MerQury");
        JTextArea urlToJar = new JTextArea("jar");
        JTextArea urlToExe = new JTextArea("exe");
        JTextArea urlToIcon = new JTextArea("ico");
        JTextArea urlToConfig = new JTextArea("json");
        JTextArea processName = new JTextArea("name");

        JCheckBox excludeCli = new JCheckBox("Exclude CLI");
        JCheckBox useCustomConfig = new JCheckBox("Use custom config\n(required for reflection)");

        JButton compile = new JButton("Compile");

        add(title);
        add(urlToJar);
        add(urlToExe);
        add(urlToIcon);
        add(processName);
        add(excludeCli);
        add(useCustomConfig);
        add(compile);
        pack();
        setLocationRelativeTo(null);
    }
}
