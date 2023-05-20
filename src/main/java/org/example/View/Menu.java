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
        JTextArea urlToJar = new JTextArea("jar");
        JTextArea urlToExe = new JTextArea("exe");
        JTextArea urlToIcon = new JTextArea("ico");
        JTextArea urlToConfig = new JTextArea("json");
        JTextArea processName = new JTextArea("name");

        JCheckBox excludeCli = new JCheckBox("Exclude CLI");
        excludeCli.setBackground(centerBackgroundColor);
        excludeCli.setForeground(Color.WHITE);
        JCheckBox useCustomConfig = new JCheckBox("Use custom config\n(required for reflection)");

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
        add(excludeCli, grid(1,16,5,1));

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
