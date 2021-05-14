package MIDI;

import java.util.ArrayList;

import MIDI.Parsing.Chord;
import MIDI.Parsing.Measure;
import MIDI.Parsing.Note;
import Graphics.Notes;
import MIDI.Parsing.Song;
import MIDI.Parsing.Tie;
import MIDI.Parsing.Voice;

public class ProcessSong
{
    ArrayList<Notes> song;
    private int timeSignitureN, timeSignitureD, dynamic ;
    private double tempo,beat;
    ArrayList<ArrayList<Double>> toTie;
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
        toTie = new ArrayList<ArrayList<Double>>();
        beat = 0;//track beat through entier song
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
                    Double duration = chords.get(k).getDuration() * timeSignitureD;//finds how many beats the note is worth

                    ArrayList<Note> notes = chords.get(k).getNotes();
                    if(chords.get(k).getDot() != 0)//acconts for dots
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
                    if(song.size() != 0)//finds notes position in song
                    {
                        while(index != song.size() && voiceBeat > song.get(index).getTime())
                        {
                            index++;
                        }
                    }

                    for(int l = 0; l < notes.size(); l++) //loop on notes
                    {
                        ArrayList<Tie> ties = notes.get(l).getTies();
                        boolean from = false;
                        boolean to = false;
                        System.out.println(ties.size());
                        int remove = 0;
                        ArrayList<Double> tie = null;
                        for(int m = 0; m < ties.size(); m++)
                        {
                            System.out.println("measure: " + ties.get(m).getMeasures() + " fraction: " + ties.get(m).getFraction());
                            if(ties.get(m).getMeasures() > 0 || (ties.get(m).getMeasures() == 0 && ties.get(m).getFraction() > 0))
                            {
                                tie = new ArrayList<Double>();
                                tie.add(Double.valueOf(index));
                                tie.add(Double.valueOf(notes.get(l).getNote()));
                                tie.add(Double.valueOf(ties.get(m).getMeasures()*timeSignitureN + ties.get(m).getFraction()*timeSignitureD + voiceBeat));                                
                                System.out.println(tie + " measure: " + ties.get(m).getMeasures() + " fraction: " + ties.get(m).getFraction());
                                to = true;
                            }
                            else 
                            {   
                                from = true;                               
                                for(int n = 0; n < toTie.size(); n++)
                                {
                                    if(notes.get(l).getNote() == toTie.get(n).get(1))//does not check if note is on the correct beat
                                    {
                                        System.out.println("before: " + song.get(toTie.get(n).get(0).intValue()));
                                        song.get(toTie.get(n).get(0).intValue()).addDuration(duration);
                                        System.out.println("after: " + song.get(toTie.get(n).get(0).intValue()));                                        
                                        if(!to)
                                        {
                                            remove = n;
                                        }
                                        n = toTie.size();
                                    }
                                }
                                
                            }
                        }
                        if(!to && from)
                        {
                            System.out.println("remove: " + remove);
                            toTie.remove(remove);
                        }
                        if(to && !from)
                        {
                            toTie.add(tie);
                        }
                        System.out.println("toTie: " + toTie);
                        if(!from)
                        {
                            song.add(index, new Notes(notes.get(l).getNote(),dynamic,timeSignitureN,timeSignitureD,voiceBeat,duration,tempo));
                        }
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