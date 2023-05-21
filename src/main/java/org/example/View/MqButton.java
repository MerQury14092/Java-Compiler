package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MqButton extends JComponent {
    private ActionListener listener;
    private Color background;
    private String text;
    private BufferedImage image;

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
    public MqButton(BufferedImage image, Dimension size){
        text = null;
        this.image = image;
        listener = (e) -> {};
        setPreferredSize(size);
        setLayout(new BorderLayout());
        add(new JLabel(text), BorderLayout.CENTER);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
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

    public void setActionListener(ActionListener actionListener){
        listener = actionListener;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(text != null) {
            g.setColor(background);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else
            g.drawImage(image,0,0,getWidth(),getHeight(),null);
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
