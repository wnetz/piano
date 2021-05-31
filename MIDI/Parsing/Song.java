package MIDI.Parsing;

import java.util.ArrayList;

public class Song 
{
    private ArrayList<Measure> bottom, top;

    public ArrayList<Measure> getBottom()
    {
        System.out.println("Song: getBottom");
        return bottom;
    }
    public ArrayList<Measure> getTop()
    {
        System.out.println("Song: getTop");
        return top;
    }    
    public Song()
    {
        System.out.println("Song");
        bottom = new ArrayList<Measure>();
        top = new ArrayList<Measure>();
    }
    @Override
    public String toString() 
    {
        return "Top: " + top.size() + " Bottom: " + bottom.size();
    }
    public void addBottom(Measure measure)
    {
        System.out.println("Song: addBottom");
        bottom.add(measure);
    }    
    public void addTop(Measure measure)
    {
        System.out.println("Song: addTop");
        top.add(measure);
    }
    public void clear()
    {
        top.clear();
        bottom.clear();
    }
    
    
}
