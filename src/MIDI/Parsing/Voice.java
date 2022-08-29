package MIDI.Parsing;

import java.util.ArrayList;

public class Voice 
{
    private ArrayList<Chord> chords;
    private double tempo;
    private int dynamic, timeSignitureD, timeSignitureN;

    public ArrayList<Chord> getChords()
    {
        //System.out.println("Voice: getChords");
        return chords;
    }  
    public double getTempo()
    {
        //System.out.println("Voice: getTempo");
        return tempo;
    }
    public int getDynamic()
    {
        //System.out.println("Voice: getDynamic");
        return dynamic;
    }
    public int[] getTimeSigniture()
    {
        //System.out.println("Voice: getTimeSigniture");
        return new int[]{timeSignitureN, timeSignitureD};
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
    public Voice()
    {
        //System.out.println("Voice");
        dynamic = 0;
        timeSignitureD = 0;
        timeSignitureN = 0;
        tempo = 0;
        chords = new ArrayList<Chord>();
    }
    public void addChord(Chord chord)
    {
        //System.out.println("Voice: addChord");
        chords.add(chord);
    } 
    public void setDynamic(int d)
    {
        //System.out.println("Voice: setDynamic");
        dynamic = d;
    }
    public void setTempo(double t)
    {
        //System.out.println("Voice: setTempo");
        tempo = t;
    }
    public void setTimeSigniture(int[] timeSigniture)
    {
        //System.out.println("Voice: setTimeSigniture");
        timeSignitureN = timeSigniture[0];
        timeSignitureD = timeSigniture[1];
    }
    
    
    

    
}
