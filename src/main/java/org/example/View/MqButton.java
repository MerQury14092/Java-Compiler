package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MqButton extends JComponent {
    private ActionListener listener;
    private Color background;
    private String text;

    public MqButton(String text, Color backgroundColor, Dimension size){
        this.text = text;
        this.background = backgroundColor;
        listener = (e) -> {};
        setPreferredSize(size);
        setLayout(new BorderLayout());
        add(new JLabel(text), BorderLayout.CENTER);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
                paintPressedComponent(getGraphics());
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(background);
        g.fillRect(0,0,getWidth(),getHeight());
    }
    protected void paintPressedComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(background.darker());
        g.fillRect(0,0,getWidth(),getHeight());
        new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }).start();
    }
}
