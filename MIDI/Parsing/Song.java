package MIDI.Parsing;

import java.util.ArrayList;

public class Song 
{
    private ArrayList<Measure> bottom, top;

    public Song()
    {
        bottom = new ArrayList<Measure>();
        top = new ArrayList<Measure>();
    }
    
    public ArrayList<Measure> getTop()
    {
        return top;
    }
    public ArrayList<Measure> getBottom()
    {
        return bottom;
    }
    
    public void addTop(Measure measure)
    {
        top.add(measure);
    }
    public void addBottom(Measure measure)
    {
        bottom.add(measure);
    }
    
    public void clear()
    {
        top.clear();
        bottom.clear();
    }
    
    @Override
    public String toString() 
    {
        return "Top: " + top.size() + " Bottom: " + bottom.size();
    }
}
