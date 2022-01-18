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
        double diff = (windowWidth*5/30.0 - windowWidth/30)/15.0;
        if(state == STATE.Opening)
        {
            menu.moveX(diff);
            width =  width + diff;
            if(width >= windowWidth*5/30.0)
            {
                width = windowWidth*5/30.0;
                state = STATE.Open;
                System.out.println(state);
            }
            q.setWidth(width);
        }
        else if(state == STATE.Closing)
        {
            menu.moveX(-diff);
            width =  width - diff;
            if(width <= windowWidth/30)
            {
                width = windowWidth/30;
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
            width = windowWidth/30;
            menu.set(2, (int)(windoHeight/60), width-4, (int)(windoHeight/60 + windoHeight/30.0) + (int)(windoHeight/120));
            menu.set1(2, (int)(windoHeight/60), (int)(width-4), (int)(windoHeight/120));
            menu.set2(2, (int)(windoHeight/60 + windoHeight/60.0), (int)(width-4), (int)(windoHeight/120));
            menu.set3(2, (int)(windoHeight/60 + windoHeight/30.0), (int)(width-4), (int)(windoHeight/120));
        }
        else if (state == STATE.Open)
        {
            width = windowWidth*5/30.0;
            menu.set((int)(width-windowWidth/30)+2, (int)(windoHeight/60), (int)(windowWidth/30-4), (int)(windoHeight/60 + windoHeight/30.0) + (int)(windoHeight/120));
            menu.set1((int)(width-windowWidth/30)+2, (int)(windoHeight/60), (int)(windowWidth/30-4), (int)(windoHeight/120));
            menu.set2((int)(width-windowWidth/30)+2, (int)(windoHeight/60 + windoHeight/60.0), (int)(windowWidth/30-4), (int)(windoHeight/120));
            menu.set3((int)(width-windowWidth/30)+2, (int)(windoHeight/60 + windoHeight/30.0), (int)(windowWidth/30-4), (int)(windoHeight/120));
        }
        height = windoHeight;        
        q.set((int)(width/4), (int)(windoHeight/60)+500, width/2.0, windoHeight/30.0);
        g.setColor(Color.blue);
        g.fillRect(0, 0, (int)width, windoHeight);
        menu.render(g,(windowWidth/30-4)/(windoHeight/120));
        q.render(g);
    }
}
