package org.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MqButton extends JComponent {
    private ActionListener listener;
    private Color background;
    private JLabel text;
    private BufferedImage image;

    public MqButton(String text, Color backgroundColor, Dimension size){
        this.text = new JLabel(text);
        this.background = backgroundColor;
        listener = (e) -> {};
        setPreferredSize(size);
        setLayout(new BorderLayout());
        add(this.text, BorderLayout.CENTER);
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
    public void setText(String text){
        this.text.setText(text);
    }
    public void setFont(Font font){
        this.text.setFont(font);
    }
    public MqButton(BufferedImage image, Dimension size){
        text = null;
        this.image = image;
        listener = (e) -> {};
        setPreferredSize(size);
        setLayout(new BorderLayout());
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
    protected void paintComponent(Graphics pG){
        super.paintComponent(pG);
        Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(text != null) {
            g.setColor(background);
            //g.fillRect(0, 0, getWidth(), getHeight());
            g.fillOval(0,0,getHeight()/2, getHeight()/2);
            g.fillOval(0, getHeight()/2, getHeight()/2, getHeight()/2);
            g.fillOval(getWidth()-getHeight()/2,0,getHeight()/2, getHeight()/2);
            g.fillOval(getWidth()-getHeight()/2, getHeight()/2, getHeight()/2, getHeight()/2);
            g.fillRect(getHeight()/4,0,getWidth()-getHeight()/2,getHeight());
            g.fillRect(0,getHeight()/4, getWidth(), getHeight()/2);

            /*g.fillOval(0,0,getHeight(),getHeight());
            g.fillOval(getWidth()-getHeight(),0,getHeight(),getHeight());
            g.fillRect(getHeight()/2, 0, getWidth()-getHeight(), getHeight());
            */
        }
        else
            g.drawImage(image,0,0,getWidth(),getHeight(),null);
    }
}
