package MIDI;

import java.util.ArrayList;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

import Graphics.PianoProject;
import MIDI.Parsing.Note;

public class MIDI implements Runnable
{	
	private ArrayList<Note> notes;
	private int device;					//-----------------------------cheat for now---------------------------------------
	private MidiDevice inputDevice;
	private Receiver receiver;
	private Sequence seq;
	private Sequencer sequencer;
	private Track currentTrack;
	private Transmitter transmitter;
	private boolean running = true;
	private PianoProject pp;

	public ArrayList<Note> getNotes() 
	{
		notes = new ArrayList<Note>();
		//System.out.println("MIDI: getNotes");
		for (int j = 0; j < currentTrack.size() - 1; j += 0)// cycle through all unread notes 
        {
            byte[] n = currentTrack.get(j).getMessage().getMessage();
            Note note = new Note((n[1] & 0xff),(n[2] & 0xff),(currentTrack.get(j).getTick()),(n[0] & 0xff));
            notes.add(note);
			pp.notePlayed((n[1] & 0xff),(n[0] & 0xff));
            // revove note so we dont read it more than once
            currentTrack.remove(currentTrack.get(j));

        }
		if(notes.size()!=0)
		{
			//System.out.println("notes: " + notes);
		}	
		return notes;		
	}
	public MIDI(PianoProject pp) 
	{
		System.out.println("MIDI");
		device = 5;
		notes = new ArrayList<Note>();
		this.pp = pp;
			

		// all MIDI devices
		/*for (int i = 0; i < MidiSystem.getMidiDeviceInfo().length; i++) {
			System.out.println(MidiSystem.getMidiDeviceInfo()[i].getName() + " - "
					+ MidiSystem.getMidiDeviceInfo()[i].getDescription());
		}*/

		try {
			// Open a connection to your input device
			inputDevice = MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[device]);// piano
			inputDevice.open();
			// Get the transmitter class from your input device
			transmitter = inputDevice.getTransmitter();

			// Open a connection to the default sequencer (as specified by MidiSystem)
			sequencer = MidiSystem.getSequencer();
			sequencer.open();			
			// Get the receiver class from your sequencer
			receiver = sequencer.getReceiver();

			// Bind the transmitter to the receiver so the receiver gets input from the
			// transmitter
			transmitter.setReceiver(receiver);

			// Create a new sequence
			seq = new Sequence(Sequence.PPQ, 60);

			// And of course a track to record the input on
			currentTrack = seq.createTrack();

			
			sequencer.setSequence(seq);


		} 
		catch (MidiUnavailableException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (InvalidMidiDataException e) 
		{
			System.out.println(e.getMessage());
		}

		// Do some sequencer settings
		sequencer.setTempoInBPM(60);
		sequencer.setTickPosition(0);
		sequencer.recordEnable(currentTrack, -1);
		// start recording
		sequencer.startRecording();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running)
		{
			this.getNotes();
		}
	}
    public void stop() 
	{
		running = false;
    }
}
