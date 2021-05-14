package Graphics;

import Graphics.Piano.BlackKey;
import Graphics.Piano.Piano;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Assignment extends JPanel implements ActionListener
{
    private NotesAnimation noteAnimation;
    private Timer update;

    public Assignment(ArrayList<Notes> notes) 
    {        
        noteAnimation = new NotesAnimation(notes);
        update = new Timer(5, this);
        update.start();//start update timer which updates every 5 mils
    }

    public void paintComponent(Graphics g)//called when repaint is called 
    {
        super.paintComponent(g);

        double height = this.getHeight() / 5;
        double width = this.getWidth() / 52.0;
        Graphics2D g2d = (Graphics2D) g;

        this.setBackground(Color.GRAY);
        
         
        noteAnimation.setTime();
        noteAnimation.paintComponent(this.getHeight()* (4 / 5.0), this.getWidth(), g2d);//paint first so piano is drawn over notes

        g2d.setStroke(new BasicStroke(2));//makes drawLine thicker
        for (int i = 0; i < Piano.NATURAL; i++)//loop on whight notes paint before black keys
        {
            g2d.setColor(Piano.whightKeys[i].getColor());
            g2d.fillRect((int) Math.round((i) * width), (int) (this.getHeight() - height), (int) (width), (int) height);
            g2d.setColor(Color.BLACK);
            g2d.drawLine((int) Math.round((i) * width), (int) (this.getHeight() - height + 1),(int) Math.round((i) * width), this.getHeight());//to better distinguish note bariers
        }

        
        for (int i = 0; i < Piano.SHARP_FLAT; i++)//loop on black keys
        {
            g2d.setColor(Piano.blackKeys[i].getColor());
            g2d.fillRect((int) Math.round(Piano.blackKeys[i].getX() * width + width * Piano.blackKeys[i].getMultiplier()),(int) (this.getHeight() - height), (int) (width * BlackKey.BLACK_KEY_WIDTH), (int) (height * .7));    
        }
    }
    public void actionPerformed(ActionEvent e)//called by timer
    {
        noteAnimation.setTime();
        this.repaint();
    }
}
