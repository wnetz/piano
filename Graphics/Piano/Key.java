package Graphics.Piano;

import java.awt.Graphics;

import Graphics.Controll.ID;
import Graphics.Controll.Objects;

import java.awt.Color;

public class Key extends Objects
{
    private int index;
    private int midi;
    private Color color = Color.white;
    private static Color defautColor = Color.white;
    public Key(int x, int y, ID id) {
        super(x, y, id);
    }
    public Key(int x, int y, ID id,int midi, int index) {
        super(x, y, id);
        this.index = index;    
        this.midi = midi;    
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g, int windowWidth, int windoHeight) {
        int width = (int)(windowWidth*29/30.0);
        g.setColor(color);
        g.fillRect(width*index/52 + windowWidth/30, windoHeight*4/5, width/52, windoHeight/5);
        g.setColor(Color.black);
        g.drawRect(width*index/52 + windowWidth/30, windoHeight*4/5, width/52, windoHeight/5);
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
