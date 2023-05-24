package org.example.View;

import java.awt.*;

import javax.swing.*;

public class PlaceholderTextField extends JTextField {

    private String placeholder;
    private Color bckgrnd, background;

    public PlaceholderTextField(final String pText, final int pColumns, Color backgroundColor) {
        super(pText, pColumns);
        bckgrnd = backgroundColor;
        super.setOpaque(false);
        super.setBackground(new Color(0, 0, 0, 0));
        background = Color.white;
    }

    public PlaceholderTextField(final int pColumns, Color backgroundColor) {
        super(pColumns);
        bckgrnd = backgroundColor;
        super.setOpaque(false);
        super.setBackground(new Color(0, 0, 0, 0));
        background = Color.white;
    }

    @Override
    protected void paintComponent(final Graphics pG) {

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(bckgrnd);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(background);
        g.fillOval(0, 0, getHeight(), getHeight());
        g.fillOval(getWidth() - getHeight(), 0, getHeight(), getHeight());
        g.fillRect(getHeight() / 2, 0, getWidth() - getHeight(), getHeight());

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            g.setColor(Color.black);
            /*g.drawString(" "+super.getText(), getInsets().left, pG.getFontMetrics()
                    .getMaxAscent() + getInsets().top);*/
            if (super.getFont().getStyle() != Font.BOLD)
                super.setFont(super.getFont().deriveFont(Font.BOLD, super.getFont().getSize()));
            super.paintComponent(pG);
            return;
        }

        g.setColor(new Color(120, 120, 120));
        g.drawString(" " + placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

    @Override
    public void setBackground(Color background) {
        this.background = background;
    }
}
