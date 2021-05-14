package MIDI.Parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {    
    private Scanner file;
    private Song song;
    private String in;

    public Parser()
    {
        song = new Song();
    }
    
    public Song parse(String name)
    {
        song.clear();//reset song
        try
        {
            file = new Scanner(new File(name));
            do
            {
                in = file.nextLine();
            }while(in.indexOf("</Part>") == -1);//skip beginig
            this.parseStaff(song.getTop());
            this.parseStaff(song.getBottom());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("could not find file " + name);
        }     
        return song;
    }
    
    private void parseStaff(ArrayList<Measure> staff)
    {
        do
        {
            in = file.nextLine();           
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
            if(in.indexOf("<voice>") != -1)
            {
                measure.addVoice(this.parseVoice());
            }
        }while(in.indexOf("</Measure>") == -1);
        return measure;
    }
    
    private Voice parseVoice()
    {
        Voice voice = new Voice();
        do
        {
            in = file.nextLine();
            if(in.indexOf("<TimeSig>") != -1)
            {
                voice.setTimeSigniture(this.parseTimeSigniture());
            }
            else if(in.indexOf("<Dynamic>") != -1)
            {
                voice.setDynamic(this.parseDynamic());
            }
            else if(in.indexOf("<Tempo>") != -1)
            {
                voice.setTempo(this.parseTempo());
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
        return voice;
    }
    
    private int[] parseTimeSigniture()
    {
        int [] timeSigniture = new int[2];

        in = file.nextLine();        
        timeSigniture[0] = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</sigN>")));  
        
        in = file.nextLine();        
        timeSigniture[1] = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</sigD>"))); 
        do
        {
            in = file.nextLine();
        }while(in.indexOf("</TimeSig>") == -1);     
        return timeSigniture;
    }
    private int parseDynamic()
    {
        do
        {
            in = file.nextLine();
        }while(in.indexOf("<velocity>") == -1);
        int dynamic = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</velocity>")));
        do
        {
            in = file.nextLine();
        }while(in.indexOf("</Dynamic>") == -1);        
        return dynamic;
    }
    private double parseTempo()
    {
        do
        {
            in = file.nextLine();
        }while(in.indexOf("<tempo>") == -1);
        double tempo = Double.parseDouble(in.substring(in.indexOf(">")+1,in.indexOf("</tempo>")));
        do
        {
            in = file.nextLine();
        }while(in.indexOf("</Tempo>") == -1);
        return tempo;
    }
    
    private Chord parseChord()
    {
        Chord chord = new Chord();
        do
        {
            in = file.nextLine();
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
        return chord;
    }
    private Chord parseRest()
    {
        Chord rest = new Chord();
        do
        {
            in = file.nextLine();
            if(in.indexOf("<dots>") != -1)
            {
                rest.setDot(Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</dots>"))));
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
            }
        }while(in.indexOf("</Rest>") == -1);
        return rest;
    }
    
    private Note parseNote()
    {
        Note note = new Note();
        do
        {
            in = file.nextLine();            
            if(in.indexOf("<next>") != -1 || in.indexOf("<prev>") != -1)
            {
                note.addTie(this.parseTie());
            }
            else if(in.indexOf("<pitch>") != -1)
            {
                note.setNote(Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</pitch>"))));
            }
        }while(in.indexOf("</Note>") == -1);
        return note;
    }
    
    private Tie parseTie()
    {
        int measure = 0;
        double fraction = 0;
        do
        {
            in = file.nextLine(); 
            if(in.indexOf("<measures>") != -1)
            {
                measure = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</measures>")));
            }
            if(in.indexOf("<fractions>") != -1)
            {
                int n = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("/")));
                int d = Integer.parseInt(in.substring(in.indexOf("/")+1,in.indexOf("</fractions>")));
                fraction = (double)n/d;
            }
        }while(in.indexOf("</next>") == -1 && in.indexOf("</prev>") == -1);
        return new Tie(measure,fraction);
    }
}
