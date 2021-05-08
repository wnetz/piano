
import java.util.ArrayList;
import java.util.Scanner;

import Graphics.Frame;
import Graphics.Piano.Piano;
import MIDI.MIDI;
import MIDI.Note;
import MIDI.MusicFiles.Parser;

public class PianoProject {
    public static void main(String args[]) { 
        Parser parser = new Parser();       
        Piano piano = new Piano();
        Frame frame = new Frame(piano);
        Scanner scan = new Scanner(System.in);
        MIDI midi = new MIDI();
        Thread midiThread = new Thread(midi);

        midiThread.setDaemon(true);
        ArrayList<Integer[]> pressed = new ArrayList<Integer[]>();
        midiThread.start();
        
        parser.parse(scan.nextLine());

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
