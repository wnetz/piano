package MIDI.Parsing;

import java.util.ArrayList;

public class Measure {    
    private ArrayList<Voice> voices;

    public Measure()
    {
        voices = new ArrayList<Voice>();
    } 
    
    public void addVoice(Voice voice)   
    {
        voices.add(voice);
    }
    
    public ArrayList<Voice> getVoices()
    {
        return voices;
    }
    
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "Voices: " + voices.size();
    }
}
