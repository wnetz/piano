package MIDI.Parsing;

import java.util.ArrayList;

public class Note 
{    
    private ArrayList<Tie> ties;
    private int note, onOff, volume;
    private long time;

    public ArrayList<Tie> getTies()
    {
        //System.out.println("Note: getTies");
        return ties;
    }
    public int getNote()
    {
        //System.out.println("Note: getNote");
        return note;
    }
    public int getOnOff()
    {
        //System.out.println("Note: getOnOff");
        return onOff;
    }
    public int getVolume()
    {
        //System.out.println("Note: getVolume");
        return volume;
    }    
    public long getTime()
    {
        //System.out.println("Note: getTime");
        return time;
    }    
    public Note()
    {
        //System.out.println("Note");
        this.note = 0;
        this.volume = 0;
        this.time = 0;
        this.onOff = 0;
        this.ties = new ArrayList<Tie>();
    }
    public Note(int note,int volume, long time, int onOff)
    {
        //System.out.println("Note2");
        this.note = note;
        this.onOff = onOff;
        this.volume = volume;
        this.time = time;        
        this.ties = new ArrayList<Tie>();
    }
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "Note: " + note + " Volume: " + volume + " On/Off: " + onOff + " Time: " + time;
    }
    public void addTie(Tie tie)
    {
        //System.out.println("Note: addTie");
        ties.add(tie);
    }
    public void setNote(int note)
    {
        //System.out.println("Note: setNote");
        this.note = note;
    }
    
    
    
}