package Graphics;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.Controll.ID;
import Graphics.Controll.Objects;
import MIDI.Parsing.Measure;

public class SongNote extends Objects{
    private int midi, index, count;
    private int windoHeight = 100;
    private double length, ratio, tempo, measures;
    private double speed = 10;
    private boolean initialize = true;
    public SongNote(double tempo, double measures, double length, ID id, Color color, int midi)
    {
        super(tempo,measures, id);
        this.length = length;
        this.color = color;
        this.midi = midi;
        this.index = midi - 21;
        this.measures = measures;
        y = measures*10;
        this.ratio = measures;
        this.tempo = tempo;
        if(midi == 60)
        {
                System.out.println(ratio);
        }

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
        double pxlsPerMeasure = windoHeight/2.5;
        y += pxlsPerMeasure*(tempo/60.0); 
        ratio = y/windoHeight;
        if(midi == 60)
        {
                System.out.println(length + " " + y + " " + ratio);
        }
               
    }
    @Override
    public void render(Graphics g, int windowWidth, int windoHeight) 
    {     
        if(initialize)
        {
                y = windoHeight*(measures/2.5)*-1;
                initialize = false;
                System.out.println(y + " " + midi);
                ratio = y / windoHeight;
        }   
        int width = (int)(windowWidth*29/30.0);  
        this.windoHeight = windoHeight;
        y=windoHeight*ratio;
            
        g.setColor(color);
        if(id == ID.note)   
        {            
            g.fillRect(width*index/52 + windowWidth/30, (int)(y-length*windoHeight/2.5), width/52, (int)(length*windoHeight/2.5));
        }
        else
        {
            g.fillRect(width*(index-count)/52 + width*3/208  + width/29, (int)y*-windoHeight, width/104, windoHeight*3/20);
        }
                
        ratio = y / windoHeight;
        
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
