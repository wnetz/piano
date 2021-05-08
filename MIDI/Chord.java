package MIDI;

import java.util.ArrayList;

public class Chord {
    private ArrayList<Note> notes;
    private int dot;
    private double duration;

    public Chord()
    {
        notes = new ArrayList<Note>();
        this.dot = 0;
        this.duration = 0;
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
    public ArrayList<Note> getNotes()
    {
        return notes;
    }
    public int getDot()
    {
        return dot;
    }
    public double getDuration()
    {
        return duration;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Notes: " + notes.size() + " Dot: " + dot + " Duration: " + duration;
    }
}
