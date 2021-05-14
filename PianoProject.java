import Graphics.Frame;
import Graphics.Piano.Piano;
import java.util.ArrayList;
import java.util.Scanner;
import MIDI.MIDI;
import MIDI.Parsing.Note;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;
import MIDI.ProcessSong;

public class PianoProject 
{
    public static void main(String args[])
    {
        
        ArrayList<Integer[]> pressed = new ArrayList<Integer[]>();
        MIDI midi = new MIDI();
        Parser parser = new Parser();       
        Piano piano = new Piano();        
        Scanner scan = new Scanner(System.in);        
        String filePath = ".\\MIDI\\Parsing\\";
        Thread midiThread = new Thread(midi);

        midiThread.setDaemon(true);
        midiThread.start();
        
        Song song = parser.parse(filePath + "Watashi_no_Uso.mscx");//scan.nextLine()
        ProcessSong ps = new ProcessSong(song);
        Frame frame = new Frame(ps.getSong());
        

        while (true) 
        {
            ArrayList<Note> notes = midi.getNotes();
            
            notes.forEach((n) -> 
            {
                pressed.add(new Integer[]{n.getNote(),n.getOnOff()});
            });
            
            piano.pressed(pressed);
            pressed.clear();

        }
        //midi.requestStop();

        //scan.close();
    }
}
