
import java.util.ArrayList;
import java.util.Scanner;

import Graphics.Frame;
import Graphics.Piano.Piano;
import MIDI.MIDI;
import MIDI.ProcessSong;
import MIDI.Parsing.Note;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;

public class PianoProject {
    public static void main(String args[]) { 
        Parser parser = new Parser();       
        Piano piano = new Piano();        
        Scanner scan = new Scanner(System.in);
        MIDI midi = new MIDI();
        Thread midiThread = new Thread(midi);
        String filePath = ".\\MIDI\\Parsing\\";

        midiThread.setDaemon(true);
        ArrayList<Integer[]> pressed = new ArrayList<Integer[]>();
        midiThread.start();
        
        Song song = parser.parse(filePath + "Watashi_no_Uso.mscx");//scan.nextLine()
        ProcessSong ps = new ProcessSong(song);
        Frame frame = new Frame(ps.getSong());
        

        while (true) {

            ArrayList<Note> notes = midi.getNotes();
            
            notes.forEach((n) -> 
            {
                pressed.add(new Integer[]{n.getNote(),n.getOnOff()});
            });
            if(notes.size() > 0)
            {
                frame.repaint();
            }
            piano.pressed(pressed);
            pressed.clear();

        }
        //midi.requestStop();

        //scan.close();
    }
}
