import java.awt.*;
import javax.swing.*;

public class Graphic extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        /*int[] xp = {50,100,150,200,250,300,350};
        int[] yp = {350,250,275,200,270,150,100};
        int np = xp.length;
        
        
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolyline(xp, yp, np);

        g2d.setFont(new Font("Comic Sans", Font.ITALIC,25));
        g2d.drawString("STONKS", 100, 100);
        
        
        g2d.drawLine(200, 200, 50, 50);

        int[] x = {100,200,300};
        int[] y = {300,127,300};
        g2d.fillPolygon(x,y,3);
        g2d.drawRect(50, 50, 300, 300);

        g2d.fillOval(50, 50, 300, 300);

        g2d.setPaint(new Color(150,250,150));*/
        GradientPaint paint = new GradientPaint(0,0,Color.RED,420,0,Color.BLUE);
        g2d.setPaint(paint);
        g2d.fillArc(50, 50, 300, 300, 180, 270);
    }
}
