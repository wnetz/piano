package Graphics;

import java.util.ArrayList;

import java.awt.Color;
import java.io.IOError;

import Graphics.Controll.Handler;
import Graphics.Controll.ID;
import MIDI.Parsing.Chord;
import MIDI.Parsing.Measure;
import MIDI.Parsing.Note;
import MIDI.Parsing.Song;
import MIDI.Parsing.Tie;
import MIDI.Parsing.Voice;

public class SongDisplay {
    private Song song;
    private Handler handler;
    private double tempo = 0;
    private double timeN = 0;
    private double timeD = 0;
    private int depth = 0;
    public SongDisplay(Song s, Handler h)
    {
        song = s;
        handler = h;        
        ArrayList<Measure> top = song.getTop();
        ArrayList<Measure> bot = song.getBottom();
        
        addHalf(top);
        addHalf(bot);
    }
    private void addHalf(ArrayList<Measure> half)
    {
        int measures = 0;
        double ratio = 0.0;
        
        for(int i = 0; i < half.size(); i++)
        {
            Measure measure = half.get(i);
            ArrayList<Voice> voices = measure.getVoices();
            for(int j = 0; j < voices.size(); j++)
            {
                Voice voice = voices.get(j);  
                if(voice.getTempo() != tempo && voice.getTempo() != 0)
                {
                    tempo = voice.getTempo();
                }     
                if(voice.getTimeSigniture()[0] != timeN && voice.getTimeSigniture()[0] != 0)
                {
                    timeN = voice.getTimeSigniture()[0];
                } 
                if(voice.getTimeSigniture()[1] != timeD && voice.getTimeSigniture()[1] != 0)
                {
                    timeD = voice.getTimeSigniture()[1];
                }                        
                ArrayList<Chord> chords = voice.getChords();
                for(int k = 0; k < chords.size(); k++)
                {
                    Chord chord = chords.get(k);
                    int dot = chord.getDot();
                    Double duration = noteLength(chord.getDuration(),dot);
                    ArrayList<Note> notes = chord.getNotes();
                    for(int l=0; l < notes.size();l++)
                    {  
                        if(notes.get(l).getTies().size() > 0)
                        {
                            ArrayList<Tie> ties = notes.get(l).getTies();
                            for(int m = 0; m < ties.size(); m++)
                            {
                                if(ties.get(m).getMeasures() > 0)
                                {
                                    depth = 0;
                                    double next = tieDuration(half, i, ties.get(m).getMeasures(), ties.get(m).getFraction(), notes.get(l).getNote()); 
                                    if(next == -1)
                                    {
                                        throw new IndexOutOfBoundsException("sdfbhdfb");
                                    }
                                    System.out.println("size " +next);
                                    duration += next;                                   
                                }
                            }
                        }
                        ID id = ID.note;
                        if(notes.get(l).getNote()%12 == 1 || notes.get(l).getNote()%12 == 3 || notes.get(l).getNote()%12 == 6 || notes.get(l).getNote()%12 == 8 || notes.get(l).getNote()%12 == 10) 
                        {
                            id = ID.sharpNote;
                        }                
                        handler.addObject(new SongNote(tempo, measures+ratio, duration, id, Color.RED, notes.get(l).getNote()));
                    }
                    //System.out.println(measures+ratio);
                    ratio += duration;
                }
            }            
            handler.addObject(new MeasureBar(tempo, measures));
            ratio = 0;
            measures++;
        }
    }
    private double noteLength(double duration, int dots)
    {
        duration = duration * timeD;
        double multiplier = 1+ ((Math.pow(2, dots))-1)/(Math.pow(2, dots));
        System.out.println( (timeN/(duration * multiplier)) + ", " + duration + ", " + (duration * multiplier) + ", " + multiplier + ", " + ((Math.pow(2, dots))-1 + "/" +Math.pow(2, dots)));
        return (duration * multiplier)/timeN;
    }
    private double tieDuration(ArrayList<Measure> half, int measure, int measures, double fraction, int note)
    {
        Double duration = 0.0;
        int nextMeasure = -1;   
        int deleteNote = -1;
        depth ++;
        for(int i = measure + measures; i < half.size(); i++)
        {
            Measure me = half.get(i);
            ArrayList<Voice> voices = me.getVoices();
            for(int j = 0; j < voices.size(); j++)
            {
                Voice v = voices.get(j);    
                if(v.getTimeSigniture()[0] != timeN && v.getTimeSigniture()[0] != 0)
                {
                    timeN = v.getTimeSigniture()[0];
                } 
                if(v.getTimeSigniture()[1] != timeD && v.getTimeSigniture()[1] != 0)
                {
                    timeD = v.getTimeSigniture()[1];
                }                      
                ArrayList<Chord> chords = v.getChords();
                for(int k = 0; k < chords.size(); k++)
                {
                    Chord c = chords.get(k);
                    int dot = c.getDot();
                    duration = noteLength(c.getDuration(),dot);
                    ArrayList<Note> notes = c.getNotes();                    
                    System.out.println("size " + notes.size() + " depth " + depth + " from " + measure + " measure " + i );
                    for(int l = 0; l < notes.size();l++)
                    {  
                        if(note == notes.get(l).getNote())
                        {
                            ArrayList<Tie> ties = notes.get(l).getTies();
                            if(ties.size() == 2)
                            {                            
                                System.out.println("two " + notes.get(l).getNote());
                                if(ties.get(1).getMeasures() == measures*-1 && ties.get(1).getFraction() == fraction*-1)
                                {
                                    deleteNote = l;
                                }                                
                                if(ties.get(0).getMeasures() > 0)
                                {        
                                    nextMeasure = ties.get(0).getMeasures();
                                    fraction = ties.get(0).getFraction();
                                }
                            }
                            if(ties.size() == 1)
                            {
                                System.out.println("one " + notes.get(l).getNote());
                                if(ties.get(0).getMeasures() == measures*-1 && ties.get(0).getFraction() == fraction*-1)
                                {
                                    deleteNote = l;
                                }
                            }
                        }
                    }                   
                    if(nextMeasure > 0)   
                    {  
                        double next = tieDuration(half, i, nextMeasure, fraction, note); 
                        if(next == -1)
                        {
                            throw new IndexOutOfBoundsException("sdfbhdfb");
                        }                 
                        duration +=  next;
                    }
                    if(deleteNote >= 0) 
                    {
                        notes.remove(deleteNote);
                        System.out.println("size " + notes.size() + " depth " + depth + " from " + measure);
                        depth--; 
                        return duration;
                    }
                    System.out.println("size " + notes.size() + " depth " + depth);
                }
            }         
        }
        return -1;
    }
}
