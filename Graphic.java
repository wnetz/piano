import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Graphic extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.GRAY);

        Graphics2D g2d = (Graphics2D) g;
        double width = this.getWidth()/52.0;
        double height = this.getHeight()/5;
        int leftover = this.getWidth() - (int)width*52;
        int blacks = 0;
        ArrayList<Double> whightx = new ArrayList<Double>();
        ArrayList<Double> blackx = new ArrayList<Double>();
        g2d.setColor(Color.WHITE);
        for(int i = 21; i <= 108; i++)
        {
            if(i%12 == 1 || i%12 == 3 || i%12 == 6 || i%12 == 8 || i%12 == 10)
            {
                blacks++;
                blackx.add((i-blacks-21)*width + 2/52.0 + leftover/52 + width*.75);                
            }
            else
            {                
                whightx.add((i-blacks-21)*width);
                g2d.fillRect((int)Math.round((i-blacks-21)*width), (int)(this.getHeight()-height), (int)(width), (int)height);
            }
            //System.out.println((int)Math.round((i-blacks-21)*width + 1/52.0 + leftover/52) + " " + ((i-blacks-21)*width + 1/52.0 + leftover/52));
        }
        //System.out.println(this.getWidth() + " " + (this.getWidth()/52.0));

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        for(int i = 0; i < whightx.size(); i++)
        {
            if(i < blackx.size())
                g2d.fillRect((int)Math.round(blackx.get(i)), (int)(this.getHeight()-height), (int)(width/2), (int)(height*.7));
            g2d.drawLine((int)Math.round(whightx.get(i)), (int)(this.getHeight()-height+1), (int)Math.round(whightx.get(i)), this.getHeight());
        }
        
        
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

        g2d.setPaint(new Color(150,250,150));
        GradientPaint paint = new GradientPaint(0,0,Color.RED,420,0,Color.BLUE);
        g2d.setPaint(paint);
        g2d.fillArc(50, 50, 300, 300, 180, 270);*/
    }
}
