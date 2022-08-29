package Graphics.Controll;

import java.awt.Color;
import java.awt.Graphics;

public class Button 
{
    protected double x,y;
    protected double width,height;
    protected Color color;
    
    public double getX()
    {
        return x;
    }
    public double getY()
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
    public void set(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
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
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }
}
