package MIDI.Parsing;

import java.util.ArrayList;

public class Chord 
{    
    private ArrayList<Note> notes;
    private double duration;
    private int dot;

    public ArrayList<Note> getNotes()
    {
        //System.out.println("Chord: getNotes");
        return notes;
    }
    public Chord()
    {        
        //System.out.println("Chord");
        this.dot = 0;
        this.duration = 0;
        notes = new ArrayList<Note>();
    }
    public double getDuration()
    {
        //System.out.println("Chord: getDuration");
        return duration;
    }
    public int getDot()
    {
        //System.out.println("Chord: getDot");
        return dot;
    }
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "Notes: " + notes.size() + " Dot: " + dot + " Duration: " + duration;
    } 
    public void addNote(Note note)
    {
        //System.out.println("Chord: addNote");
        notes.add(note);
    }
    public void setDot(int d) 
    {
        //System.out.println("Chord: setDot");
        dot = d;
    }
    public void setDuration(double d) 
    {
        //System.out.println("Chord: setDuration");
        duration = d;
    }
    
    
    
    
}
