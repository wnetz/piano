package Graphics;

import java.awt.Color;
import java.awt.Graphics;

import Graphics.Controll.ID;
import Graphics.Controll.Objects;

public class MeasureBar extends Objects
{
    private int windoHeight = 100;
    private double ratio, tempo, measures;
    private boolean initialize = true;
    static boolean pause = false;

    public MeasureBar(double tempo, double measures)
    {
        super(tempo,measures, ID.measureBar);
        this.color = Color.DARK_GRAY;
        this.measures = measures;
        y = measures*10;
        this.ratio = measures;
        this.tempo = tempo;
    }
    @Override
    public void tick() 
    {
        double pxlsPerMeasure = windoHeight/2.5;
        if(!pause)
        {
                y += pxlsPerMeasure*(tempo/60.0); 
                ratio = y/windoHeight;
        }
    }
    @Override
    public void render(Graphics g, int windowWidth, int windoHeight, double windowRatio) 
    {
        if(initialize)
        {
            y = windoHeight*(measures/2.5)*-1;
            initialize = false;
            //System.out.println(y + " " + midi);
            ratio = y / windoHeight;
        }   
        width = windowWidth*windowRatio; //should probably be deleted 
        length = 2;
        this.windoHeight = windoHeight;
        y=windoHeight*ratio;
            
        g.setColor(color);
        x = -5;
        g.drawLine(-5, (int)y, (int)windowWidth + 5, (int)y);
                
        ratio = y / windoHeight;        
    }
    @Override
    public void setColor(Color color) 
    {
        this.color = color;        
    }
    @Override
    public Color getColor() 
    {
        return color;
    }
    @Override
    public void setDefaultColor()
    {
        
    }
    public static void pause()
    {
        pause = true;
    }
    public static void play()
    {
        pause = false;
    }
}
