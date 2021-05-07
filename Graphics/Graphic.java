package Graphics;

import java.awt.*;

import javax.swing.*;

import Graphics.Piano.Piano;

public class Graphic extends JPanel {
    Piano piano;

    public Graphic(Piano piano) {
        this.piano = piano;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.GRAY);

        Graphics2D g2d = (Graphics2D) g;
        double width = this.getWidth() / 52.0;
        double height = this.getHeight() / 5;
        // ArrayList<Double> whightx = new ArrayList<Double>();
        // ArrayList<Double> blackx = new ArrayList<Double>();        
        for (int i = 0; i < Piano.NATURAL; i++) {
            g2d.setColor(piano.whightKeys[i].getColor());
            g2d.fillRect((int) Math.round((i) * width), (int) (this.getHeight() - height), (int) (width), (int) height);
            g2d.setColor(Color.BLACK);
            g2d.drawLine((int) Math.round((i) * width), (int) (this.getHeight() - height + 1),(int) Math.round((i) * width), this.getHeight());
        }

        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < Piano.SHARP_FLAT; i++) {
            g2d.setColor(piano.blackKeys[i].getColor());
            g2d.fillRect((int) Math.round(piano.blackKeys[i].getX() * width + width * piano.blackKeys[i].getMultiplier()),(int) (this.getHeight() - height), (int) (width / 2), (int) (height * .7));    
        }

        /*
         * int[] xp = {50,100,150,200,250,300,350}; int[] yp =
         * {350,250,275,200,270,150,100}; int np = xp.length;
         * 
         * 
         * g2d.setStroke(new BasicStroke(2)); g2d.drawPolyline(xp, yp, np);
         * 
         * g2d.setFont(new Font("Comic Sans", Font.ITALIC,25)); g2d.drawString("STONKS",
         * 100, 100);
         * 
         * 
         * g2d.drawLine(200, 200, 50, 50);
         * 
         * int[] x = {100,200,300}; int[] y = {300,127,300}; g2d.fillPolygon(x,y,3);
         * g2d.drawRect(50, 50, 300, 300);
         * 
         * g2d.fillOval(50, 50, 300, 300);
         * 
         * g2d.setPaint(new Color(150,250,150)); GradientPaint paint = new
         * GradientPaint(0,0,Color.RED,420,0,Color.BLUE); g2d.setPaint(paint);
         * g2d.fillArc(50, 50, 300, 300, 180, 270);
         */
    }
}
