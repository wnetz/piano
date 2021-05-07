package Graphics;

import javax.swing.*;

import Graphics.Piano.Piano;

public class Frame extends JFrame {
    public Graphic graphic;

    public Frame(Piano piano) {
        graphic = new Graphic(piano);
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        this.setVisible(true);
    }
}
