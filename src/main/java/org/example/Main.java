package org.example;

import org.example.View.Menu;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        Menu menu;
        if(args.length > 0)
            menu = new Menu(args[0]);
        else
            menu = new Menu("");
        menu.setVisible(true);
    }
}