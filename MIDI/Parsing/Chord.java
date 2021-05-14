package MIDI.Parsing;

import java.util.ArrayList;

public class Chord 
{    
    private int dot;
    private double duration;
    private ArrayList<Note> notes;

    public Chord()
    {        
        this.dot = 0;
        this.duration = 0;
        notes = new ArrayList<Note>();
    }
    
    public void setDot(int d) 
    {
        dot = d;
    }
    public void setDuration(double d) 
    {
        duration = d;
    }
    public void addNote(Note note)
    {
        notes.add(note);
    }
    
    public int getDot()
    {
        return dot;
    }
    public double getDuration()
    {
        return duration;
    }
    public ArrayList<Note> getNotes()
    {
        return notes;
    }

    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "Notes: " + notes.size() + " Dot: " + dot + " Duration: " + duration;
    }
}
