package MIDI.Parsing;

import java.util.ArrayList;

public class Measure 
{    
    private ArrayList<Voice> voices;

    public ArrayList<Voice> getVoices()
    {
        //System.out.println("Measure: getVoices");
        return voices;
    }
    public Measure()
    {
        //System.out.println("Measure");
        voices = new ArrayList<Voice>();
    } 
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "Voices: " + voices.size();
    }    
    public void addVoice(Voice voice)   
    {
        //System.out.println("Measure: addVoice");
        voices.add(voice);
    }
}
