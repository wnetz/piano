package Graphics.Piano;

import java.awt.Graphics;

import Graphics.Controll.ID;

import java.awt.Color;

public class SharpKey extends Key
{
    private int count;
    private static Color defautColor = Color.black;
    public SharpKey(int x, int y, ID id) {
        super(x, y, ID.sharpKey);
        color = Color.black;
        noteID=id;
        id = ID.sharpKey;
    }
    public SharpKey(int x, int y, ID id, int index, int count) {
        super(x, y, ID.sharpKey);
        color = Color.black;
        this.index = index-1;  
        this.count = count; 
        midi = index+21;    
        noteID=id;   
        id = ID.sharpKey;
    }

    @Override
    public void render(Graphics g, int windowWidth, int windoHeight, double windowRatio) {
        int width = (int)(windowWidth*windowRatio);
        g.setColor(color);
        g.fillRect(width*(index-count)/52 + width*3/208  + width/29, windoHeight*4/5, width/104, windoHeight*3/20);
        
    }
    
    public void setDefaultColor()
    {
        color = defautColor;
    }
    
}