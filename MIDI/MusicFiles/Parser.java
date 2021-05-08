package MIDI.MusicFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import MIDI.Chord;
import MIDI.Measure;
import MIDI.Note;
import MIDI.Song;
import MIDI.Voice;

public class Parser {
    private Song song;
    private Scanner file;
    private String in;

    public Parser()
    {
        song = new Song();
    }
    public Song parse(String name)
    {
        song.clear();
        try
        {
            file = new Scanner(new File(name));
            do
            {
                in = file.nextLine();
            }while(in.indexOf("</Part>") == -1);
            System.out.println(" top");
            this.parseStaff(song.getTop());
            System.out.println("/top");
            System.out.println(" bottom");
            this.parseStaff(song.getBottom());
            System.out.println("/bottom");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("could not find file " + name);
        } 
        System.out.println(song);     
        return song;
    }
    private void parseStaff(ArrayList<Measure> staff)
    {
        do
        {
            in = file.nextLine();
            //System.out.println(" st" + in);
        }while(in.indexOf("<Measure>") == -1);
        do
        {
            in = file.nextLine();            
            //System.out.println("/st" + in);
            if(in.indexOf("<Measure>") != -1)
            {
                staff.add(this.parseMeasure());
            }
        }while(in.indexOf("</Staff>") == -1);
        
        
    }
    private Measure parseMeasure()
    {
        Measure measure = new Measure();
        do
        {
            in = file.nextLine();
            //System.out.println(" st" + in);
            if(in.indexOf("<voice>") != -1)
            {
                measure.addVoice(this.parseVoice());
            }
        }while(in.indexOf("</Measure>") == -1);
        System.out.println(measure);
        return measure;
    }
    private Voice parseVoice()
    {
        Voice voice = new Voice();
        do
        {
            in = file.nextLine();
            //System.out.println(" pv" + in);
            if(in.indexOf("<TimeSig>") != -1)
            {
                voice.setTimeSigniture(this.parseTimeSigniture());
                System.out.println("time signiture: " + voice.getTimeSigniture()[0]+ "/" + voice.getTimeSigniture()[1]);
            }
            else if(in.indexOf("<Dynamic>") != -1)
            {
                voice.setDynamic(this.parseDynamic());
                System.out.println("dynamic: " + voice.getDynamic());
            }
            else if(in.indexOf("<Tempo>") != -1)
            {
                voice.setTempo(this.parseTempo());
                System.out.println("tempo: " + voice.getTempo());
            }
            else if(in.indexOf("<Chord>") != -1)
            {
                voice.addChord(this.parseChord());
            }
            else if(in.indexOf("<Rest>") != -1)
            {
                voice.addChord(this.parseRest());
            }
        }while(in.indexOf("</voice>") == -1); 
        System.out.println(voice);       
        return voice;
    }
    private int[] parseTimeSigniture()
    {
        int [] timeSigniture = new int[2];
        in = file.nextLine();
        //System.out.println("tsn" + in);
        timeSigniture[0] = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</sigN>")));  
        in = file.nextLine();
        //System.out.println("tsd" + in);
        timeSigniture[1] = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</sigD>"))); 
        do
        {
            in = file.nextLine();
            //System.out.println("/ts" + in);
        }while(in.indexOf("</TimeSig>") == -1);     
        return timeSigniture;
    }
    private int parseDynamic()
    {
        do
        {
            in = file.nextLine();
            //System.out.println(" dy" + in);
        }while(in.indexOf("<velocity>") == -1);
        int dynamic = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</velocity>")));
        do
        {
            in = file.nextLine();
            //System.out.println("/dy" + in);
        }while(in.indexOf("</Dynamic>") == -1);
        
        return dynamic;
    }
    private double parseTempo()
    {
        do
        {
            in = file.nextLine();
            //System.out.println(" tm" + in);
        }while(in.indexOf("<tempo>") == -1);
        double tempo = Double.parseDouble(in.substring(in.indexOf(">")+1,in.indexOf("</tempo>")));
        do
        {
            in = file.nextLine();
            //System.out.println("/tm" + in);
        }while(in.indexOf("</Tempo>") == -1);
        return tempo;
    }
    private Chord parseChord()
    {
        Chord chord = new Chord();
        do
        {
            in = file.nextLine();
            //System.out.println(" ch" + in);
            if(in.indexOf("<dots>") != -1)
            {
                chord.setDot(Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</dots>"))));
            }
            else if(in.indexOf("<durationType>") != -1)
            {
                String duration = in.substring(in.indexOf(">")+1,in.indexOf("</durationType>"));
                switch(duration)
                {
                    case "whole":
                        chord.setDuration(1.0);
                        break;
                    case "half":
                        chord.setDuration(1/2.0);
                        break;
                    case "quarter":
                        chord.setDuration(1/4.0);
                        break;
                    case "eighth":
                        chord.setDuration(1/8.0);
                        break;
                    case "16th":
                        chord.setDuration(1/16.0);
                        break;
                    case "32nd":
                        chord.setDuration(1/32.0);
                        break;
                }
            }
            else if(in.indexOf("<Note>") != -1)
            {
                chord.addNote(this.parseNote());                
            }
        }while(in.indexOf("</Chord>") == -1);
        System.out.println(chord);
        return chord;
    }
    private Chord parseRest()
    {
        Chord rest = new Chord();
        do
        {
            in = file.nextLine();
            //System.out.println(" rs" + in);
            if(in.indexOf("<dots>") != -1)
            {
                rest.setDot(Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</dots>"))));
                System.out.println("dots: " + rest.getDot());
            }
            else if(in.indexOf("<durationType>") != -1)
            {
                String duration = in.substring(in.indexOf(">")+1,in.indexOf("</durationType>"));
                switch(duration)
                {
                    case "whole":
                        rest.setDuration(1.0);
                        break;
                    case "half":
                        rest.setDuration(1/2.0);
                        break;
                    case "quarter":
                        rest.setDuration(1/4.0);
                        break;
                    case "eighth":
                        rest.setDuration(1/8.0);
                        break;
                    case "16th":
                        rest.setDuration(1/16.0);
                        break;
                    case "32nd":
                        rest.setDuration(1/32.0);
                        break;
                }
                System.out.println("duration: " + rest.getDuration());
            }
        }while(in.indexOf("</Rest>") == -1);
        return rest;
    }
    private Note parseNote()
    {
        do
        {
            in = file.nextLine();
            //System.out.println(" no" + in);
        }while(in.indexOf("<pitch>") == -1);
        int note = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</pitch>")));
        do
        {
            in = file.nextLine();
            //System.out.println("/no" + in);
        }while(in.indexOf("</Note>") == -1);
        System.out.println("Pitch: " + note);
        return new Note(note, 0, 0, 0);
    }
}
