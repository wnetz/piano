package MIDI;

import java.util.ArrayList;

public class Chord {
    private ArrayList<Note> notes;
    private boolean dot;
    private double duration;

    public Chord(boolean dot, double duration)
    {
        notes = new ArrayList<Note>();
        this.dot = dot;
        this.duration = duration;
    }
    public void addNote(Note note)
    {
        notes.add(note);
    }
    public ArrayList<Note> getNotes()
    {
        return notes;
    }
    public boolean getDot()
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
