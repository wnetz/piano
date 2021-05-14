package MIDI.Parsing;

import java.util.ArrayList;

public class Note {
    private int note, onOff, volume;
    private long time;
    private ArrayList<Tie> ties;

    public Note(int note,int volume, long time, int onOff)
    {
        this.note = note;
        this.onOff = onOff;
        this.volume = volume;
        this.time = time;        
        this.ties = new ArrayList<Tie>();
    }
    public Note()
    {
        this.note = 0;
        this.volume = 0;
        this.time = 0;
        this.onOff = 0;
        this.ties = new ArrayList<Tie>();
    }
   
    public int getNote()
    {
        return note;
    }
    public int getOnOff()
    {
        return onOff;
    }
    public int getVolume()
    {
        return volume;
    }    
    public long getTime()
    {
        return time;
    }
    public ArrayList<Tie> getTies()
    {
        return ties;
    }
    
    public void setNote(int note)
    {
        this.note = note;
    }
    public void addTie(Tie tie)
    {
        ties.add(tie);
    }
    
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "Note: " + note + " Volume: " + volume + " On/Off: " + onOff + " Time: " + time;
    }
}