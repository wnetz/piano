package Graphics;

import java.util.ArrayList;

import java.awt.Color;

import Graphics.Controll.Handler;
import Graphics.Controll.ID;
import MIDI.Parsing.Chord;
import MIDI.Parsing.Measure;
import MIDI.Parsing.Note;
import MIDI.Parsing.Song;
import MIDI.Parsing.Voice;

public class SongDisplay {
    private Song song;
    private Handler handler;
    public SongDisplay(Song s, Handler h)
    {
        song = s;
        handler = h;
        int measures = 0;
        double ratio = 0.0;
        double tempo = 0;
        ArrayList<Measure> top = song.getTop();
        for(int i = 0; i < top.size(); i++)
        {
            Measure measure = top.get(i);
            ArrayList<Voice> voices = measure.getVoices();
            for(int j = 0; j < voices.size(); j++)
            {
                Voice voice = voices.get(j);  
                if(voice.getTempo() != tempo && voice.getTempo() != 0)
                {
                    tempo = voice.getTempo();
                }                           
                ArrayList<Chord> chords = voice.getChords();
                for(int k = 0; k < chords.size(); k++)
                {
                    Chord chord = chords.get(k);
                    ArrayList<Note> notes = chord.getNotes();
                    for(int l=0; l < notes.size();l++)
                    {                        
                        handler.addObject(new SongNote(tempo, measures+ratio, chord.getDuration(), ID.note, Color.RED, notes.get(l).getNote()));
                    }
                    //System.out.println(measures+ratio);
                    ratio += chord.getDuration();
                }
            }
            ratio = 0;
            measures++;
        }
    }
}
