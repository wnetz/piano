package MIDI.Parsing;

import java.util.ArrayList;

public class Voice {
    private int timeSignitureN, timeSignitureD, dynamic;
    private double tempo;
    private ArrayList<Chord> chords;

    public Voice()
    {
        this.timeSignitureN = 0;
        this.timeSignitureD = 0;
        this.dynamic = 0;
        this.tempo = 0;
        chords = new ArrayList<Chord>();
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
    @Override
    public String toString() {
        // TODO Auto-generated method stub
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
