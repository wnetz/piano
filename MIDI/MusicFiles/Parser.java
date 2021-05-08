package MIDI.MusicFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import MIDI.Measure;
import MIDI.Song;

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
        }while(in.indexOf("<Staff") == -1);
        do
        {
            in = file.nextLine();
            this.parseVoice();
        }while(in.indexOf("/Staff") == -1);
        
        
    }
    private Measure parseVoice()
    {
        int[] timeSigniture = new int[2];
        int dynamic = 0;
        double tempo = 0;
        do
        {
            in = file.nextLine();
        }while(in.indexOf("<voice>") == -1);
        do
        {
            in = file.nextLine();
            if(in.indexOf("<TimeSig>") != -1)
            {
                timeSigniture = this.parseTimeSigniture(timeSigniture);
                System.out.println(timeSigniture[0]+ " " + timeSigniture[1]);
            }
            else if(in.indexOf("<Dynamic>") != -1)
            {

            }
            else if(in.indexOf("<Tempo>") != -1)
            {

            }
            else if(in.indexOf("<Chord>") != -1)
            {

            }
        }while(in.indexOf("</voice>") == -1);
        return new Measure(timeSigniture[0], timeSigniture[1], dynamic, tempo);
    }
    private int[] parseTimeSigniture(int[] timeSigniture)
    {
        in = file.nextLine();
        timeSigniture[0] = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</sigN>")));  
        in = file.nextLine();
        timeSigniture[1] = Integer.parseInt(in.substring(in.indexOf(">")+1,in.indexOf("</sigD>")));      
        return timeSigniture;
    }
}
