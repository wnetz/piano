package Graphics.Controll;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Color;
import java.awt.Graphics;


public class Menu  extends MouseAdapter
{
    private double width=0;
    private double height=0;
    private Button q;
    private Hamberger menu;
    public enum STATE 
    {
        Open, Opening, Closed, Closing
    };
    private STATE state = STATE.Closed;
    public Menu()
    {
        q = new Button();
        q.setColor(Color.black);
        menu = new Hamberger();
        menu.setColor(Color.black);
    }
    public void mousePressed(MouseEvent e)
    {
        double mx = e.getX();
        double my = e.getY();
        boolean onbutton = mouseOver(mx, my, menu.getX(), menu.getY(),menu.getWidth(),menu.getheight());
        System.out.println(mx + " " + my + " " + menu.getX() + " " + menu.getY() + " " + (menu.getX() + menu.getWidth()) + " " + (menu.getY() + menu.getheight()));
        if(state == STATE.Open && onbutton)
        {
            state = STATE.Closing;
        }
        else if(state == STATE.Closed && onbutton)
        {
            state = STATE.Opening;
        }
        System.out.println(state + " " + onbutton);
    }
    public void mouseReleased(MouseEvent e)
    {

    }
    private boolean mouseOver(double mx, double my, double x, double y, double bw, double bh)
    {
        return mx>x && mx<x+bw && my>y && my<y+bh;
    }
    public void tick(int windowWidth, int windoHeight)
    {
        double diff = (windowWidth*5 - windowWidth)/15.0;
        if(state == STATE.Opening)
        {
            menu.moveX(diff);
            width =  width + diff;
            if(width >= windowWidth*5)
            {
                width = windowWidth*5;
                state = STATE.Open;
                System.out.println(state);
            }
            q.setWidth(width);
        }
        else if(state == STATE.Closing)
        {
            menu.moveX(-diff);
            width =  width - diff;
            if(width <= windowWidth)
            {
                width = windowWidth;
                state = STATE.Closed;
                System.out.println(state);
            }
            q.setWidth(width);
        }
        
    }
    public void render(Graphics g, int windowWidth, int windoHeight)
    {
        if(state == STATE.Closed)
        {
            width = windowWidth;
            menu.set(2, (windoHeight/60), width-4, (windoHeight/60 + windoHeight/30.0) + (windoHeight/120));
            menu.set1(2, (windoHeight/60), (width-4), (windoHeight/120));
            menu.set2(2, (windoHeight/60 + windoHeight/60.0), (width-4), (windoHeight/120));
            menu.set3(2, (windoHeight/60 + windoHeight/30.0), (width-4), (windoHeight/120));
        }
        else if (state == STATE.Open)
        {
            width = windowWidth*5;
            menu.set((width-windowWidth)+2, (windoHeight/60), (windowWidth-4), (windoHeight/60 + windoHeight/30.0) + (windoHeight/120));
            menu.set1((width-windowWidth)+2, (windoHeight/60), (windowWidth-4), (windoHeight/120));
            menu.set2((width-windowWidth)+2, (windoHeight/60 + windoHeight/60.0), (windowWidth-4), (windoHeight/120));
            menu.set3((width-windowWidth)+2, (windoHeight/60 + windoHeight/30.0), (windowWidth-4), (windoHeight/120));
        }
        height = windoHeight;        
        q.set((width/4), (windoHeight/60)+500, width/2.0, windoHeight);
        g.setColor(Color.blue);
        g.fillRect(0, 0, (int)width, windoHeight);
        menu.render(g,(windowWidth-4)/(windoHeight/120));
        q.render(g);
    }
}
