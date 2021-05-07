
import java.util.ArrayList;
import java.util.Scanner;

import Graphics.Frame;
import Graphics.Piano.Piano;
import MIDIInput.MIDI;
import MIDIInput.Note;

public class PianoProject {
    public static void main(String args[]) {        
        Piano piano = new Piano();
        Frame frame = new Frame(piano);

        Scanner scan = new Scanner(System.in);
        MIDI midi = new MIDI();
        Thread midiThread = new Thread(midi);
        ArrayList<Integer[]> pressed = new ArrayList<Integer[]>();
        midiThread.start();
        while (true) {

            /*ArrayList<Note> notes = midi.getNotes();
            
            notes.forEach((n) -> 
            {
                System.out.println(n);
                pressed.add(new Integer[]{n.note,n.onOff});
            });
            if(notes.size() != 0)
            {
                frame.repaint();
            }
            piano.pressed(pressed);
            pressed.clear();*/

        }
        //midi.requestStop();

        //scan.close();
    }
}
