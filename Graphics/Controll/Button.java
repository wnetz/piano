package Graphics.Controll;

import java.awt.Color;
import java.awt.Graphics;

public class Button 
{
    protected int x,y;
    protected double width,height;
    protected Color color;
    
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public double getWidth()
    {
        return width;
    }
    public double getheight()
    {
        return height;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public void set(int x, int y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setWidth(double width)
    {
        this.width = width;
    }
    public void setHeight(double height)
    {
        this.height = height;
    }
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, (int)width, (int)height);
    }
}
