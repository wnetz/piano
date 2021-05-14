package MIDI;

import java.util.ArrayList;

import MIDI.Parsing.Chord;
import MIDI.Parsing.Measure;
import MIDI.Parsing.Note;
import Graphics.Notes;
import MIDI.Parsing.Song;
import MIDI.Parsing.Voice;

public class ProcessSong
{
    ArrayList<Notes> song;
    private int timeSignitureN, timeSignitureD, dynamic ;
    private double tempo,beat;
    public ProcessSong(Song s)
    {
        song = new ArrayList<Notes>();
        timeSignitureN = 0;
        timeSignitureD = 0;
        dynamic = 0;        
        tempo = 0;
        this.processSong(s);
    }
    public ArrayList<Notes> getSong() {
        return song;
    }

    private void processSong(Song s)
    {
        this.processHalf(s.getTop());
        this.processHalf(s.getBottom());
        //System.out.println(song);
    }
    private void processHalf(ArrayList<Measure> half)
    {
        beat = 0;
        for(int i = 0; i < half.size(); i++) //loop on measures
        {   
            ArrayList<Voice> voices = half.get(i).getVoices();
            for(int j = 0; j < voices.size(); j++) //loop on voices
            {
                double voiceBeat = beat;
                this.updateValues(voices.get(j));
                ArrayList<Chord> chords = voices.get(j).getChords();
                for(int k = 0; k < chords.size(); k++) //loop on chords
                {
                    ArrayList<Note> notes = chords.get(k).getNotes();                
                    Double duration = chords.get(k).getDuration() * timeSignitureD;
                    if(chords.get(k).getDot() != 0)
                    {
                        int d = 1;
                        double multiplier = .5;
                        while(d != chords.get(k).getDot())
                        {
                            multiplier += Math.pow(.5, d);
                            d++;
                        }
                        duration += duration*multiplier;
                    }
                    int index = 0;
                    if(song.size() != 0)
                    {
                        while(index != song.size() && voiceBeat > song.get(index).getTime())
                        {
                            index++;
                        }
                    }
                    for(int l = 0; l < notes.size(); l++) //loop on notes
                    {
                        song.add(index, new Notes(notes.get(l).getNote(),dynamic,timeSignitureN,timeSignitureD,voiceBeat,duration,tempo));
                    }
                    voiceBeat += duration;
                }
            }
            beat += timeSignitureN;
        }
    }
    private void updateValues(Voice voice)
    {
        int [] timeSig = voice.getTimeSigniture();
        if(timeSig[0] != 0 && timeSig[1] != 0)
        {
            timeSignitureN = timeSig[0];
            timeSignitureD = timeSig[1];
        }
        if(voice.getDynamic() != 0)
        {
            dynamic = voice.getDynamic();
        }
        if(voice.getTempo() != 0)
        {
            tempo = Math.round(voice.getTempo()*60);
            tempo = tempo/60.0;
        }
    }
}