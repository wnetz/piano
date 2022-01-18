package Graphics;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.Controll.ID;
import Graphics.Controll.Objects;

public class SongNote extends Objects{
    private int midi, index, count;
    private double length;
    private double speed = 10;
    public SongNote(int x, double y, long length, ID id, Color color, int midi)
    {
        super(x,y, id);
        this.length = length;
        this.color = color;
        this.midi = midi;
        this.index = midi - 21;
        this.y = this.y*100;

        switch(midi%12)
        {
            case 0: index = 2 + index/12*7;
                    break;
            case 1: index = 2 + index/12*7;
                    count = 1 + index/12*5;
                    break;
            case 2:index = 3 + index/12*7;
                    break;
            case 3:index = 3 + index/12*7;
                    count = 2 + index/12*5;
                    break;
            case 4:index = 4 + index/12*7;
                    break;
            case 5:index = 5 + index/12*7;
                    break;
            case 6:index = 5 + index/12*7;
                    count = 3 + index/12*5;
                    break;
            case 7:index = 6 + index/12*7;
                    break;
            case 8:index = 6 + index/12*7;
                    count = 4 + index/12*5;
                    break;
            case 9:index = 0 + index/12*7;
                    break;
            case 10:index = 0 + index/12*7;
                    count = 0 + index/12*5;
            break;
            case 11:index = 1 + index/12*7;
                    break;

        }
    }
    @Override
    public void tick() {
        y += speed; 
        System.out.println(y);        
    }
    @Override
    public void render(Graphics g, int windowWidth, int windoHeight) {
        int width = (int)(windowWidth*29/30.0);   
            
        g.setColor(color);
        if(id == ID.note)   
        {            
            g.fillRect(width*index/52 + windowWidth/30, (int)y, width/52, windoHeight*3/20);
        }
        else
        {
            g.fillRect(width*(index-count)/52 + width*3/208  + width/29, (int)y*-windoHeight, width/104, windoHeight*3/20);
        }
        
        
        
    }
    @Override
    public void setColor(Color color) {
        this.color = color;
        
    }
    @Override
    public Color getColor() {
        return color;
    }
    @Override
    public void setDefaultColor() {
        
    }
}
