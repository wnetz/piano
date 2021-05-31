package MIDI;

import java.util.ArrayList;
import javafx.concurrent.Task;
import javax.sound.midi.Track;
import MIDI.Parsing.Note;

public class GetMIDI extends Task<ArrayList<Note>>
{
    private ArrayList<Note> notes;
    private Track track;

    @Override
    protected ArrayList<Note> call()
    {
        System.out.println("GetMIDI: call>");
        for (int j = 0; j < track.size() - 1; j += 0)// cycle through all unread notes 
        {
            byte[] n = track.get(j).getMessage().getMessage();
            Note note = new Note((n[1] & 0xff),(n[2] & 0xff),(track.get(j).getTick()),(n[0] & 0xff));
            notes.add(note);

            // revove note so we dont read it more than once
            track.remove(track.get(j));
        }
        System.out.println("GetMIDI: call<");
        return notes;
    }
    public GetMIDI(Track currentTrack)
    {
        System.out.println("GetMIDI");
        track = currentTrack;
        notes = new ArrayList<Note>();
    }
}
