package MIDI;

import Graphics.Notes;
import java.util.ArrayList;
import MIDI.Parsing.Chord;
import MIDI.Parsing.Measure;
import MIDI.Parsing.Note;
import MIDI.Parsing.Song;
import MIDI.Parsing.Tie;
import MIDI.Parsing.Voice;

public class ProcessSong
{
    private int dynamic, timeSignitureD, timeSignitureN ;
    private double beat, tempo;
    ArrayList<Notes> song;
    ArrayList<ArrayList<Double>> toTie;

    public ProcessSong(Song s)
    {
        dynamic = 0; 
        timeSignitureD = 0; 
        timeSignitureN = 0; 
        beat = 0;             
        tempo = 0;
        song = new ArrayList<Notes>();
        toTie = new ArrayList<ArrayList<Double>>();
        
        this.processSong(s);
    }
    
    public ArrayList<Notes> getSong() {
        return song;
    }

    private void processSong(Song s)
    {
        this.processHalf(s.getTop());
        this.processHalf(s.getBottom());
    }
    
    private void processHalf(ArrayList<Measure> half)
    {
        beat = 0;//track beat through entier song
        toTie = new ArrayList<ArrayList<Double>>();

        for(int i = 0; i < half.size(); i++) //loop on measures
        {   
            ArrayList<Voice> voices = half.get(i).getVoices();
            for(int j = 0; j < voices.size(); j++) //loop on voices
            {
                double voiceBeat = beat;//track beat through measure
                this.updateValues(voices.get(j));

                ArrayList<Chord> chords = voices.get(j).getChords();
                for(int k = 0; k < chords.size(); k++) //loop on chords
                {
                    int index = 0;
                    Double duration = chords.get(k).getDuration() * timeSignitureD;//finds how many beats the note is worth
                    ArrayList<Note> notes = chords.get(k).getNotes();

                    duration += this.dotLength(duration, chords.get(k).getDot());//acconts for dots
                    
                    while(index != song.size() && voiceBeat > song.get(index).getTime())//finds notes position in song
                    {
                        index++;
                    }

                    for(int l = 0; l < notes.size(); l++) //loop on notes
                    {
                        this.addNotes(notes.get(l), index, voiceBeat, duration);//deals with notes and ties
                    }
                    voiceBeat += duration;
                }
            }
            beat += timeSignitureN;//add a measurs worth of beats
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
    private double dotLength(double duration,int dots)
    {
        int dot = 1;
        double multiplier;

        if(dots != 0)
        {
            multiplier = .5;
            while(dot != dots)
            {
                multiplier += Math.pow(.5, dot);
                dot++;
            }
        }
        else
        {
            multiplier = 0;
        }
        return duration*multiplier;
    }
    
    private void addNotes(Note note, int index, double beat, double duration)
    {
        boolean from = false;
        boolean to = false;
        int remove = 0;
        ArrayList<Double> tie = null;
        ArrayList<Tie> ties = note.getTies();
        
        for(int i = 0; i < ties.size(); i++)//loop on ties
        {
            if(ties.get(i).getMeasures() > 0 || (ties.get(i).getMeasures() == 0 && ties.get(i).getFraction() > 0))//if tie goes forward
            {
                tie = new ArrayList<Double>();
                tie.add(Double.valueOf(index));
                tie.add(Double.valueOf(note.getNote()));
                tie.add(Double.valueOf(ties.get(i).getMeasures()*timeSignitureN + ties.get(i).getFraction()*timeSignitureD + beat));
                to = true;
            }
            else 
            {   
                from = true;                               
                for(int n = 0; n < toTie.size(); n++)//loop on current ties
                {
                    if(note.getNote() == toTie.get(n).get(1))//does not check if note is on the correct beat
                    {
                        song.get(toTie.get(n).get(0).intValue()).addDuration(duration);                                       
                        if(!to)
                        {
                            remove = n;
                        }
                        n = toTie.size();
                    }
                }
                
            }
        }
        if(!to && from)//if the tie does not continue remove
        {
            toTie.remove(remove);
        }
        if(to && !from)//if start of tie add to list
        {
            toTie.add(tie);
        }
        if(!from)//only add if note is start of a tie or does not tie
        {
            song.add(index, new Notes(dynamic, note.getNote(), timeSignitureD, timeSignitureN, tempo, duration, beat));
        }
    }
}