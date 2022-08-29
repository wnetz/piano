package Graphics.Controll;

import java.awt.Graphics;

public class Hamberger extends Button{
    private double x11,x12,x21,x22,x31,x32,y11,y12,y21,y22,y31,y32;
    public void set1(double x11,double y11,double x12,double y12)
    {
        this.x11 = x11;
        this.y11 = y11;
        this.x12 = x12;
        this.y12 = y12;
    }
    public void set2(double x21,double y21,double x22,double y22)
    {
        this.x21 = x21;
        this.y21 = y21;
        this.x22 = x22;
        this.y22 = y22;
    }
    public void set3(double x31,double y31,double x32,double y32)
    {
        this.x31 = x31;
        this.y31 = y31;
        this.x32 = x32;
        this.y32 = y32;
    }
    public void moveX(double xdiff)
    {
        x11 += xdiff;
        x21 += xdiff;
        x31 += xdiff;
    }
    public void render(Graphics g,double r)
    {
        g.setColor(super.color);
        g.fillRoundRect((int)x11, (int)y11, (int)x12, (int)y12,(int)r,(int)r);
        g.fillRoundRect((int)x21, (int)y21, (int)x22, (int)y22,(int)r,(int)r);
        g.fillRoundRect((int)x31, (int)y31, (int)x32, (int)y32,(int)r,(int)r);
    }
}