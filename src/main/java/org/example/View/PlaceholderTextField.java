package org.example.View;

import java.awt.*;

import javax.swing.*;

public class PlaceholderTextField extends JTextField {

    private String placeholder;

    public PlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }
    public PlaceholderTextField(final int pColumns){
        super(pColumns);
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(120,120,120));
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}
