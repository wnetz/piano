package Graphics.Controll;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Objects 
{
    protected double x = 0;
    protected double width = 0;
    protected double y = 0;
    protected double length = 0;
    protected ID id;
    protected int velx, vely;
    protected Color color;

    public Objects(double x, double y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g, int windowWidth, int windoHeight, double windowRatio);
    public abstract void setColor(Color color);
    public abstract Color getColor();
    public abstract void setDefaultColor();

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setVelX(int velx)
    {
        this.velx = velx;
    }
    public void setVelY(int vely)
    {
        this.vely = vely;
    }
    public void setID(ID id)
    {
        this.id = id;
    }
    

    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public int getVelX()
    {
        return velx;
    }
    public int getVelY()
    {
        return vely;
    }
    public ID getID()
    {
        return id;
    }    
}
