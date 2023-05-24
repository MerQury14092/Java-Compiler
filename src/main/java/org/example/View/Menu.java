package org.example.View;

import org.example.interfaces.CommandOutput;
import org.example.logic.Compiler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

public class Menu extends JFrame implements CommandOutput, WindowListener {
    private final JTextArea cmdOutput;
    private final MqButton compile;
    private final ActionListener buttonActionListener;
    private final Font jetbrains_mono;
    private final Compiler interpreter;
    private final GridBagConstraints locator;

    public Menu(String initUrlToJar) throws IOException, FontFormatException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);
        int dotSize = 21;
        gridLayout.columnWidths = IntStream.generate(() -> dotSize).limit(16).toArray();
        gridLayout.rowHeights = IntStream.generate(() -> dotSize).limit(22).toArray();
        setTitle("JCompiler");
        setIconImage(ImageIO.read(Objects.requireNonNull(Menu.class.getResourceAsStream("/ico.png"))).getSubimage(160, 160, 640 - 160 * 2, 640 - 160 * 2));
        addWindowListener(this);

        locator = new GridBagConstraints();

        JFileChooser fileChooser = new JFileChooser();

        interpreter = new Compiler();

        BufferedImage logo = ImageIO.read(Objects.requireNonNull(Menu.class.getResourceAsStream("/merq_logo.png"))).getSubimage(134, 134, 768 - 134 * 2, 768 - 134 * 2);
        BufferedImage fileChooserIcon = ImageIO.read(Objects.requireNonNull(Menu.class.getResourceAsStream("/fileChooser.png")));

        jetbrains_mono = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/font.ttf"))).deriveFont(Font.PLAIN, 12);

        Color topBackgroundColor = new Color(79, 80, 84);
        Color centerBackgroundColor = new Color(32, 33, 37);
        Color bottomBackgroundColor = new Color(22, 23, 27);
        Color textColor = new Color(145, 146, 150);
        Color textFieldBackgroundColor = new Color(230, 230, 230);

        JLabel author = new JLabel("Created by MerQury");
        author.setFont(jetbrains_mono);

        JLabel title = new JLabel("Java Compiler");
        title.setFont(jetbrains_mono.deriveFont(Font.BOLD, 24));
        title.setForeground(new Color(1, 176, 117));
        //title.setFont(new Font(author.getFont().getName(), Font.BOLD, 25));

        PlaceholderTextField urlToJar = new PlaceholderTextField(initUrlToJar, 22, centerBackgroundColor);
        urlToJar.setFont(jetbrains_mono);
        urlToJar.setColumns(35);
        urlToJar.setPlaceholder("Path to jar (required)");

        PlaceholderTextField urlToExe = new PlaceholderTextField(initUrlToJar.replace(".jar", ".exe"), 22, centerBackgroundColor);
        urlToExe.setFont(jetbrains_mono);
        urlToExe.setColumns(35);
        urlToExe.setPlaceholder("Path to output exe (required)");

        PlaceholderTextField urlToIcon = new PlaceholderTextField(22, centerBackgroundColor);
        urlToIcon.setFont(jetbrains_mono);
        urlToIcon.setColumns(35);
        urlToIcon.setPlaceholder("Path to icon (optional)");

        PlaceholderTextField urlToConfig = new PlaceholderTextField(22, centerBackgroundColor);
        urlToConfig.setFont(jetbrains_mono);
        urlToConfig.setColumns(35);
        urlToConfig.setPlaceholder("Path to config (optional)");

        PlaceholderTextField processName = new PlaceholderTextField(22, centerBackgroundColor);
        processName.setFont(jetbrains_mono);
        processName.setColumns(35);
        processName.setPlaceholder("Process name (optional)");

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
        excludeCliText.setFont(jetbrains_mono.deriveFont(Font.PLAIN, 12));
        excludeCliText.setForeground(textColor);

        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(logo.getScaledInstance(84, 84, Image.SCALE_SMOOTH)));

        JPanel topBackground = new JPanel();
        topBackground.setPreferredSize(new Dimension(16 * dotSize, 4 * dotSize));
        topBackground.setBackground(topBackgroundColor);

        JPanel centerBackground = new JPanel();
        centerBackground.setPreferredSize(new Dimension(16 * dotSize, 22 * dotSize));
        centerBackground.setBackground(centerBackgroundColor);

        JPanel cmdBackground = new JPanel();
        cmdBackground.setPreferredSize(new Dimension(16 * dotSize, 4 * dotSize));
        cmdBackground.setBackground(bottomBackgroundColor);

        compile = new MqButton("compile", Color.white, new Dimension(60, 20));
        compile.setFont(jetbrains_mono.deriveFont(Font.PLAIN, 12));

        MqButton jarFileChoose = new MqButton(fileChooserIcon, new Dimension(21, 21));
        jarFileChoose.setActionListener((e) -> {
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Jar files", "jar"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                urlToJar.setText(fileChooser.getSelectedFile().getPath());
                urlToExe.setText(urlToJar.getText().replace(".jar", ".exe"));
            }
        });

        MqButton exeFileChoose = new MqButton(fileChooserIcon, new Dimension(21, 21));
        exeFileChoose.setActionListener((e) -> {
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (urlToExe.getText().isEmpty())
                fileChooser.setSelectedFile(new File(urlToJar.getText().replace(".jar", ".exe")));
            else
                fileChooser.setSelectedFile(new File(urlToExe.getText()));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Executable files", "exe"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                urlToExe.setText(fileChooser.getSelectedFile().getPath());
        });

        MqButton icoFileChoose = new MqButton(fileChooserIcon, new Dimension(21, 21));
        icoFileChoose.setActionListener((e) -> {
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (urlToIcon.getText().isEmpty())
                fileChooser.setSelectedFile(new File(urlToJar.getText().replace(".jar", ".ico")));
            else
                fileChooser.setSelectedFile(new File(urlToIcon.getText()));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Ico files", "ico"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                urlToIcon.setText(fileChooser.getSelectedFile().getPath());
        });

        MqButton configFileChoose = new MqButton(fileChooserIcon, new Dimension(21, 21));
        configFileChoose.setActionListener((e) -> {
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (urlToConfig.getText().isEmpty())
                fileChooser.setSelectedFile(new File(urlToJar.getText().replace(".jar", ".ico")));
            else
                fileChooser.setSelectedFile(new File(urlToConfig.getText()));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Config files", "json"));
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                urlToConfig.setText(fileChooser.getSelectedFile().getPath());
        });

        buttonActionListener = (e) ->
                interpreter.run(this,
                        urlToJar.getText(),
                        urlToExe.getText(),
                        urlToIcon.getText(),
                        urlToConfig.getText(),
                        processName.getText(),
                        excludeCliCheckbox.isSelected() + "");
        compile.setActionListener((e) -> {
            buttonActionListener.actionPerformed(e);
            compile.setText("doing...");
            compile.setActionListener((a) -> {
            });
        });

        cmdOutput = new JTextArea("I'm output text area");
        cmdOutput.setFont(jetbrains_mono.deriveFont(Font.PLAIN, 13));
        cmdOutput.setBackground(bottomBackgroundColor);
        cmdOutput.setForeground(textColor);
        cmdOutput.setEditable(false);

        add(author, grid(8, 0, 10, 1));
        add(title, grid(4, 1, 10, 2));
        add(compile, grid(11, 16, 4, 1));
        add(excludeCliCheckbox, grid(1, 15, 1, 1));
        add(excludeCliText, grid(2, 15, 5, 1));
        add(urlToJar, grid(1, 5, 12, 1));
        add(jarFileChoose, grid(14, 5, 1, 1));
        add(urlToExe, grid(1, 7, 12, 1));
        add(exeFileChoose, grid(14, 7, 1, 1));
        add(urlToIcon, grid(1, 9, 12, 1));
        add(icoFileChoose, grid(14, 9, 1, 1));
        add(urlToConfig, grid(1, 11, 12, 1));
        add(configFileChoose, grid(14, 11, 1, 1));
        add(processName, grid(1, 13, 12, 1));
        add(logoLabel, grid(0, 0, 4, 4));
        add(cmdOutput, grid(0, 19, 22, 1));

        add(topBackground, grid(0, 0, 16, 4));
        add(cmdBackground, grid(0, 18, 16, 4));
        add(centerBackground, grid(0, 0, 16, 22));

        pack();
        setLocationRelativeTo(null);
    }

    private GridBagConstraints grid(int x, int y, int width, int height) {
        locator.gridx = x;
        locator.gridy = y;
        locator.gridwidth = width;
        locator.gridheight = height;
        return locator;
    }

    @Override
    public void write(String res) {
        cmdOutput.setText(res);
    }

    @Override
    public void done() {
        compile.setText("compile");
        compile.setActionListener((e) -> {
            buttonActionListener.actionPerformed(e);
            compile.setText("doing...");
            compile.setActionListener((a) -> {
            });
        });
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        interpreter.interrupt();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
