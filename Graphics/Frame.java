package Graphics;

import java.util.ArrayList;

import javax.swing.*;

public class Frame extends JFrame {
    public Assignment graphic;

    public Frame(ArrayList<Notes> notes) {
        graphic = new Assignment(notes);
        this.setSize(1500, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        this.setVisible(true);
    }
}
