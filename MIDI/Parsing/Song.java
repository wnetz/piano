package MIDI.Parsing;

import java.util.ArrayList;

public class Song {
    private ArrayList<Measure> top, bottom;

    public Song()
    {
        top = new ArrayList<Measure>();
        bottom = new ArrayList<Measure>();
    }
    public void addTop(Measure measure)
    {
        top.add(measure);
    }
    public void addBottom(Measure measure)
    {
        bottom.add(measure);
    }
    public ArrayList<Measure> getTop()
    {
        return top;
    }
    public ArrayList<Measure> getBottom()
    {
        return bottom;
    }
    public void clear()
    {
        top.clear();
        bottom.clear();
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Top: " + top.size() + " Bottom: " + bottom.size();
    }
}
