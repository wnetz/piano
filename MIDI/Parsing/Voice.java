package MIDI.Parsing;

import java.util.ArrayList;

public class Voice {
    private int dynamic, timeSignitureD, timeSignitureN;
    private double tempo;
    private ArrayList<Chord> chords;

    public Voice()
    {
        dynamic = 0;
        timeSignitureD = 0;
        timeSignitureN = 0;
        tempo = 0;
        chords = new ArrayList<Chord>();
    }
        
    public int[] getTimeSigniture()
    {
        return new int[]{timeSignitureN, timeSignitureD};
    }
    public int getDynamic()
    {
        return dynamic;
    }
    public double getTempo()
    {
        return tempo;
    }
    public ArrayList<Chord> getChords()
    {
        return chords;
    }
    
    public void setTimeSigniture(int[] timeSigniture)
    {
        timeSignitureN = timeSigniture[0];
        timeSignitureD = timeSigniture[1];
    }
    public void setDynamic(int d)
    {
        dynamic = d;
    }
    public void setTempo(double t)
    {
        tempo = t;
    }
    public void addChord(Chord chord)
    {
        chords.add(chord);
    }
    

    @Override
    public String toString()
    {
        String out = "Chords: " + chords.size();
        if(timeSignitureN != 0)
        {
            out = out + " Time Signiture: " + timeSignitureN + "/" + timeSignitureD;
        }
        if(dynamic != 0)
        {
            out = out + " Dynamic: " + dynamic;
        }
        if(tempo != 0)
        {
            out = out + " BPM: " + tempo*60;
        }
        return  out;
    }
}
