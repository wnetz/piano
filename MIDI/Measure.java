package MIDI;

import java.util.ArrayList;

public class Measure {
    private int timeSignitureN, timeSignitureD, dynamic;
    private double tempo;
    private ArrayList<Chord> chords;

    public Measure(int timeSignitureN, int timeSignitureD, int dynamic, double tempo)
    {
        this.timeSignitureN = timeSignitureN;
        this.timeSignitureD = timeSignitureD;
        this.dynamic = dynamic;
        this.tempo = tempo;
        chords = new ArrayList<Chord>();
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
    public double setTempo()
    {
        return tempo;
    }
    public ArrayList<Chord> addChord()
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
