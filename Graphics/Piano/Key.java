package Graphics.Piano;

import java.awt.Graphics;

import Graphics.Controll.ID;
import Graphics.Controll.Objects;

import java.awt.Color;

public class Key extends Objects
{
    protected int index;
    protected int midi;
    protected int length;
    protected int width;
    protected ID noteID;
    protected Color color = Color.white;
    protected static Color defautColor = Color.white;
    public Key(int x, int y, ID id) 
    {
        super(x, y, ID.key);
        noteID=id;
        id = ID.key;
    }
    public Key(int x, int y, ID id,int midi, int index) 
    {
        super(x, y, ID.key);
        this.index = index;    
        this.midi = midi; 
        noteID=id;   
        id = ID.key;
    }

    @Override
    public void tick() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g, int windowWidth, int windoHeight) 
    {
        int w = (int)(windowWidth*29/30.0);
        width = w/52;
        length = windoHeight/5;
        x = w*index/52 + windowWidth/30;
        y = windoHeight*4/5;
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)width, (int)length);
        g.setColor(Color.black);
        g.drawRect((int)x, (int)y, (int)width, (int)length);
    }

    public int getMidi()
    {
        return midi;
    }
    public Color getColor()
    {
        return color;
    }
    public ID getNoteId()
    {
        return noteID;
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
