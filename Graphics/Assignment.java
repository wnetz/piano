package Graphics;

import java.awt.*;

import javax.swing.*;

import Graphics.Piano.Piano;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Assignment extends JPanel implements ActionListener
{
    Timer update;
    NotesAnimation noteAnimation;
    public Assignment(ArrayList<Notes> notes) 
    {
        update = new Timer(5, this);
        noteAnimation = new NotesAnimation(notes);
        update.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.GRAY);

        Graphics2D g2d = (Graphics2D) g;
        double width = this.getWidth() / 52.0;
        double height = this.getHeight() / 5; 
        noteAnimation.setTime();
        noteAnimation.paintComponent(g2d, this.getHeight()* (4 / 5.0), this.getWidth());

        for (int i = 0; i < Piano.NATURAL; i++) {
            g2d.setColor(Piano.whightKeys[i].getColor());
            g2d.fillRect((int) Math.round((i) * width), (int) (this.getHeight() - height), (int) (width), (int) height);
            g2d.setColor(Color.BLACK);
            g2d.drawLine((int) Math.round((i) * width), (int) (this.getHeight() - height + 1),(int) Math.round((i) * width), this.getHeight());
        }

        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < Piano.SHARP_FLAT; i++) {
            g2d.setColor(Piano.blackKeys[i].getColor());
            g2d.fillRect((int) Math.round(Piano.blackKeys[i].getX() * width + width * Piano.blackKeys[i].getMultiplier()),(int) (this.getHeight() - height), (int) (width / 2), (int) (height * .7));    
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        noteAnimation.setTime();
        this.repaint();
    }
}
