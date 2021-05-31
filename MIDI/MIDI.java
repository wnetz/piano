package MIDI;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;
import MIDI.Parsing.Note;

public class MIDI extends ScheduledService<ArrayList<Note>> 
{	
	private ArrayList<Note> notes;
	private boolean get;				//track state
	private int device;					//-----------------------------cheat for now---------------------------------------
	private MidiDevice inputDevice;
	private Receiver receiver;
	private Sequence seq;
	private Sequencer sequencer;
	private Task<ArrayList<Note>> t;
	private Track currentTrack;
	private Transmitter transmitter;

	public ArrayList<Note> getNotes() 
	{
		System.out.println("MIDI: getNotes");
		get = true;//request to get notes	
		return notes;		
	}
	public MIDI() 
	{
		System.out.println("MIDI");
		get = false;
		device = 5;
		notes = new ArrayList<Note>();
		

		setOnSucceeded(s ->
		{
			t.cancel();
		});
		setOnReady(s -> 
		{
			//System.out.println(get);
			if(get)
			{
				System.out.println("cancle");
				t.cancel();
				
			}
		});
		

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
	protected Task<ArrayList<Note>> createTask()
	{	
		System.out.println("MIDI: createTask");	
		//System.out.println("new");
		t = new GetMIDI(currentTrack);
		t.run();
		try
		{
			notes = (ArrayList<Note>)t.get();
			if(notes.size()!=0)
			{
				//System.out.println(notes);
			}
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
		catch(ExecutionException e)
		{
			System.out.println(e.getMessage());
		}
		
		return t;
	}
	public void done()
	{
		System.out.println("MIDI: done");	
		get = false;
		notes.clear();
	}
}
