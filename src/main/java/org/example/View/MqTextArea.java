package org.example.View;

import javax.swing.*;
import java.awt.*;

// Импортируем нужные Java-классы

public class MqTextArea extends JTextArea {
    // Создаем новый класс MqTextArea, который расширяет класс JTextArea.

    private String placeholderText; // Объявляем приватную переменную placeholderText
    private Color placeholderColor = Color.GRAY; // Объявляем приватную переменную placeholderColor и устанавливаем ее значение на серый цвет
    private int fixedWidth; // Объявляем приватную переменную fixedWidth
    private Color backgroundColor; // Объявляем приватную переменную backgroundColor

    public MqTextArea(String placeholderText, int fixedWidth) {
        // Создаем конструктор, который принимает строку placeholderText и целое число fixedWidth в качестве аргументов

        this(placeholderText, fixedWidth, null);
        // Вызываем другой конструктор с тремя аргументами
    }

    public MqTextArea(String placeholderText, int fixedWidth, Color backgroundColor) {
        // Создаем конструктор, который принимает строку placeholderText, целое число fixedWidth и объект Color backgroundColor в качестве аргументов

        super(placeholderText); // Вызываем конструктор родительского класса с параметром placeholderText
        this.placeholderText = placeholderText; // Устанавливаем переменную placeholderText в значение параметра placeholderText
        this.fixedWidth = fixedWidth; // Устанавливаем переменную fixedWidth в значение параметра fixedWidth
        this.backgroundColor = backgroundColor; // Устанавливаем переменную backgroundColor в значение параметра backgroundColor
        setOpaque(true); // Устанавливаем атрибут opaque в true
        setEditable(false); // Устанавливаем атрибут editable в false
        setWrapStyleWord(true); // Включаем перенос по словам
        setLineWrap(true); // Включаем перенос строк
        setFont(getFont().deriveFont(Font.PLAIN)); // Устанавливаем шрифт на обычный
        setPreferredSize(new Dimension(fixedWidth, getPreferredSize().height)); // Устанавливаем предпочитаемый размер текстовой области
    }

    public void setBackground(Color backgroundColor) {
        // Создаем метод, который принимает объект Color backgroundColor в качестве аргумента

        this.backgroundColor = backgroundColor; // Задаем переменной backgroundColor значение параметра backgroundColor
        super.setBackground(backgroundColor); // Вызываем метод setBackground родительского класса с аргументом backgroundColor
    }

    protected void paintComponent(Graphics g) {
        // Создаем защищенный метод paintComponent, который принимает объект Graphics g в качестве аргумента

        if (!isEditable() && getText().isEmpty()) {
            // Проверяем, что текстовая область не редактируется и что в ней нет текста

            g.setColor(placeholderColor); // Задаем цвет графического объекта в значение placeholder color
            g.drawString(placeholderText, getInsets().left, g.getFontMetrics().getHeight() + getInsets().top);
            // Рисуем текст-заполнитель в качестве значения переменной placeholderText и располагаем его в левом и верхнем отступах текстовой области
        } else {
            setBackground(backgroundColor); // Задаем цвет фона текстовой области
        }
        super.paintComponent(g); // Вызываем метод paintComponent родительского класса с аргументом Graphics g
    }

    public String getPlaceholderText() {
        // Создаем метод, который возвращает строку текста-заполнителя

        return placeholderText; // Возвращаем значение переменной placeholderText
    }

    public void setPlaceholderText(String placeholderText) {
        // Создаем метод, который принимает строку placeholderText в качестве аргумента

        this.placeholderText = placeholderText; // Устанавливаем значение переменной placeholderText как параметр placeholderText
    }

    public Color getPlaceholderColor() {
        // Создаем метод, который возвращает цвет текста-заполнителя

        return placeholderColor; // Возвращаем значение переменной placeholderColor
    }

    public void setPlaceholderColor(Color placeholderColor) {
        // Создаем метод, который принимает объект Color placeholderColor в качестве аргумента

        this.placeholderColor = placeholderColor; // Устанавливаем значение переменной placeholderColor как параметр placeholderColor
    }
}