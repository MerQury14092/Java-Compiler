package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

public class Menu extends JFrame {
    private GridBagConstraints locator;
    public Menu(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);
        int dotSize = 20;
        gridLayout.columnWidths = IntStream.generate(() -> dotSize).limit(16).toArray();
        gridLayout.rowHeights = IntStream.generate(() -> dotSize).limit(22).toArray();
        setTitle("JCompiler");

        locator = new GridBagConstraints();

        JFileChooser fileChooser = new JFileChooser();

        Color topBackgroundColor = new Color(0,255,0);
        Color centerBackgroundColor = new Color(0,0,255);
        Color bottomBackgroundColor = new Color(255,0,0);

        JLabel author = new JLabel("Created by MerQury");
        JLabel title = new JLabel("Java Compiler");
        title.setFont(new Font(author.getFont().getName(), Font.BOLD, 25));
        MqTextArea urlToJar = new MqTextArea("jar",5, Color.white);
        MqTextArea urlToExe = new MqTextArea("exe",5, Color.white);
        MqTextArea urlToIcon = new MqTextArea("ico",5, Color.white);
        MqTextArea urlToConfig = new MqTextArea("json",5, Color.white);
        MqTextArea processName = new MqTextArea("name",5, Color.white);

        JCheckBox excludeCliCheckbox = new JCheckBox();
        excludeCliCheckbox.setBackground(centerBackgroundColor);
        JLabel excludeCliText = new JLabel("Exclude CLI");
        excludeCliText.setForeground(Color.WHITE);
        JCheckBox useCustomConfigCheckbox = new JCheckBox();
        JLabel useCustomConfigText = new JLabel("Use custom config (required for reflection)");
        useCustomConfigCheckbox.setBackground(centerBackgroundColor);
        useCustomConfigText.setForeground(Color.white);

        JPanel topBackground = new JPanel();
        topBackground.setPreferredSize(new Dimension(16*dotSize, 4*dotSize));
        topBackground.setBackground(topBackgroundColor);
        JPanel centerBackground = new JPanel();
        centerBackground.setPreferredSize(new Dimension(16*dotSize, 22*dotSize));
        centerBackground.setBackground(centerBackgroundColor);
        JPanel cmdBackground = new JPanel();
        cmdBackground.setPreferredSize(new Dimension(16*dotSize, 4*dotSize));
        cmdBackground.setBackground(bottomBackgroundColor);


        JButton compile = new JButton("Compile");

        add(author, grid(8,0,10,1));
        add(title, grid(4,1,9,2));
        add(compile, grid(11,16,4,1));
        add(excludeCliCheckbox, grid(1,15,1,1));
        add(useCustomConfigCheckbox, grid(1,13,1,1));
        add(excludeCliText, grid(2,15,4,1));
        add(useCustomConfigText, grid(2,13,13,1));
        add(urlToJar, grid(1,5,11,1));
        add(urlToExe, grid(1,7,11,1));
        add(urlToIcon, grid(1,9,11,1));
        add(processName, grid(1,11,11,1));

        add(topBackground, grid(0,0,16,4));
        add(cmdBackground, grid(0,18,16,4));
        add(centerBackground, grid(0,0,16,22));

        pack();
        setLocationRelativeTo(null);
    }

    private GridBagConstraints grid(int x, int y){
        locator.gridx = x;
        locator.gridy = y;
        locator.gridwidth = 1;
        locator.gridheight = 1;
        return locator;
    }

    private GridBagConstraints grid(int x, int y, int width, int height){
        locator.gridx = x;
        locator.gridy = y;
        locator.gridwidth = width;
        locator.gridheight = height;
        return locator;
    }
}
