package Graphics.Piano;

import java.awt.Graphics;

import Graphics.Controll.ID;
import Graphics.Controll.Objects;

import java.awt.Color;

public class SharpKey extends Objects
{
    int index;
    int count;
    int midi;
    private Color color = Color.black;
    private static Color defautColor = Color.black;
    public SharpKey(int x, int y, ID id) {
        super(x, y, id);
    }
    public SharpKey(int x, int y, ID id, int index, int count) {
        super(x, y, id);
        this.index = index-1;  
        this.count = count; 
        midi = index;    
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g, int windowWidth, int windoHeight) {
        int width = (int)(windowWidth*29/30.0);
        g.setColor(color);
        g.fillRect(width*(index-count)/52 + width*3/208  + width/29, windoHeight*4/5, width/104, windoHeight*3/20);
        
    }

    public int getMidi()
    {
        return midi;
    }
    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
    
    public void setDefaultColor()
    {
        color = defautColor;
    }
    
}