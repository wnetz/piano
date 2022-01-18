package Graphics.Controll;

import java.awt.Graphics;

public class Hamberger extends Button{
    private int x11,x12,x21,x22,x31,x32,y11,y12,y21,y22,y31,y32;
    public void set1(int x11,int y11,int x12,int y12)
    {
        this.x11 = x11;
        this.y11 = y11;
        this.x12 = x12;
        this.y12 = y12;
    }
    public void set2(int x21,int y21,int x22,int y22)
    {
        this.x21 = x21;
        this.y21 = y21;
        this.x22 = x22;
        this.y22 = y22;
    }
    public void set3(int x31,int y31,int x32,int y32)
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
    public void render(Graphics g,int r)
    {
        g.setColor(super.color);
        g.fillRoundRect(x11, y11, x12, y12,r,r);
        g.fillRoundRect(x21, y21, x22, y22,r,r);
        g.fillRoundRect(x31, y31, x32, y32,r,r);
    }
}