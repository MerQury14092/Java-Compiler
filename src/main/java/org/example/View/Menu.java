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
        int dotSize = 21;
        gridLayout.columnWidths = IntStream.generate(() -> dotSize).limit(16).toArray();
        gridLayout.rowHeights = IntStream.generate(() -> dotSize).limit(22).toArray();
        setTitle("JCompiler");

        locator = new GridBagConstraints();

        JFileChooser fileChooser = new JFileChooser();

        Color topBackgroundColor = new Color(79,80,84);
        Color centerBackgroundColor = new Color(32,33,37);
        Color bottomBackgroundColor = new Color(22,23,27);
        Color textColor = new Color(145,146,150);
        Color textFieldBackgroundColor = new Color(230,230,230);

        JLabel author = new JLabel("Created by MerQury");

        JLabel title = new JLabel("Java Compiler");
        title.setForeground(new Color(1,176,117));
        title.setFont(new Font(author.getFont().getName(), Font.BOLD, 25));

        PlaceholderTextField urlToJar = new PlaceholderTextField(22);
        urlToJar.setPlaceholder("Path to jar");

        PlaceholderTextField urlToExe = new PlaceholderTextField(22);
        urlToExe.setPlaceholder("Path to output exe");

        PlaceholderTextField urlToIcon = new PlaceholderTextField(22);
        urlToIcon.setPlaceholder("Path to icon");

        PlaceholderTextField urlToConfig = new PlaceholderTextField(22);
        urlToConfig.setPlaceholder("Path to config");

        PlaceholderTextField processName = new PlaceholderTextField(22);
        processName.setPlaceholder("Process name");

        urlToJar.setBorder(null);
        urlToJar.setBackground(textFieldBackgroundColor);

        urlToExe.setBorder(null);
        urlToExe.setBackground(textFieldBackgroundColor);

        urlToIcon.setBorder(null);
        urlToIcon.setBackground(textFieldBackgroundColor);

        urlToConfig.setBorder(null);
        urlToConfig.setBackground(textFieldBackgroundColor);

        processName.setBorder(null);
        processName.setBackground(textFieldBackgroundColor);

        JCheckBox excludeCliCheckbox = new JCheckBox();
        excludeCliCheckbox.setBackground(centerBackgroundColor);

        JLabel excludeCliText = new JLabel("Exclude CLI  ");
        excludeCliText.setForeground(textColor);
        JCheckBox useCustomConfigCheckbox = new JCheckBox();

        JLabel useCustomConfigText = new JLabel("Use custom config (required for reflection)");
        useCustomConfigCheckbox.setBackground(centerBackgroundColor);
        useCustomConfigText.setForeground(textColor);

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
        add(useCustomConfigText, grid(2,13,12,1));
        add(urlToJar, grid(1,5,12,1));
        add(urlToExe, grid(1,7,12,1));
        add(urlToIcon, grid(1,9,12,1));
        add(processName, grid(1,11,12,1));

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
