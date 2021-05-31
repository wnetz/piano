package Graphics;

import javafx.scene.paint.Color;

public class Notes 
{
        
    private Color color;
    private double bps, duration, time;
    private int dynamic, note, timeSignitureD, timeSignitureN;

    public Color getColor()
    {
        //System.out.println("Notes: getColor");
        return color;
    }
    public double getBPS()
    {
        //System.out.println("Notes: getBPS");
        return bps;
    }
    public double getDuration()
    {
        //System.out.println("Notes: getDuration");
        return duration;
    }
    public double getTime()
    {
        //System.out.println("Notes: getTime");
        return time;
    }
    public int getDynamic()
    {
        //System.out.println("Notes: getDynamic");
        return dynamic;
    }
    public int getNote()
    {
        //System.out.println("Notes: getNote");
        return note;
    }
    public int getTimeSignitureD()
    {
        //System.out.println("Notes: getTimeSignitureD");
        return timeSignitureD;
    }
    public int getTimeSignitureN()
    {
        //System.out.println("Notes: getTimeSignitureN");
        return timeSignitureN;
    }
    public Notes(int dynamic, int note, int timeSignitureD, int timeSignitureN, double bps, double duration, double time, Color color)
    {
        //System.out.println("Notes");
        this.timeSignitureD = timeSignitureD;
        this.note = note;
        this.timeSignitureN = timeSignitureN;
        this.dynamic = dynamic;
        this.bps = bps;
        this.duration = duration;
        this.time = time;
        this.color = color;
    }
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "N: " + note + " T: " + time + " D: " + duration ;
    }
    public void addDuration(double duration)
    {
        //System.out.println("Notes: addDuration");
        this.duration += duration;
    }
    
}
