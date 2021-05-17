package MIDI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ExecutionException;

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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

public class MIDI extends ScheduledService<ArrayList<Note>> 
{	
	private boolean get;				//track state
	private boolean stopRequested;		//track state
	private int device;					//-----------------------------cheat for now---------------------------------------
	private ArrayList<Note> notes;
	private ArrayList<Note> returnNotes;//used to prevent consecutive writes
	private MidiDevice inputDevice;
	private Receiver receiver;
	private Sequence seq;
	private Sequencer sequencer;
	private Track currentTrack;
	private Transmitter transmitter;
	private Task<ArrayList<Note>> t;

	public MIDI() 
	{
		get = false;
		stopRequested = false;
		device = 5;
		notes = new ArrayList<Note>();
		returnNotes = new ArrayList<Note>();
		

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
		//System.out.println("new");
		t = new GetMIDI(currentTrack);
		t.run();
		try
		{
			notes = (ArrayList)t.get();
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

	public synchronized void requestStop()//sent from main when it watns to shutdown 
	{
		stopRequested = true;
	}
	private synchronized boolean isStopRequested()//these two are "synchronized" to avoid concurent writes 
	{
		return this.stopRequested;
	}
	
	public ArrayList<Note> getNotes() 
	{
		get = true;//request to get notes	
		return notes;		
	}
	public void done()
	{
		get = false;
		notes.clear();
	}

	/*@Override
	public void run() 
	{
		
		while (!isStopRequested())//runs infinetly until stop requested 
		{	
			System.out.println("in");		
			if(get)//if get requested
			{	
				//update return notes and notes
				returnNotes.clear();
				notes.forEach((n)-> returnNotes.add(n));//deep copy
				notes.clear();
				notesProperty.set(0);

				get = false;//give permission			
			}

			if(currentTrack.size() != 0)
			{
				notesProperty.set(1);
			}
			
			for (int j = 0; j < currentTrack.size() - 1; j += 0)// cycle through all unread notes 
			{
				byte[] n = currentTrack.get(j).getMessage().getMessage();
				Note note = new Note((n[1] & 0xff),(n[2] & 0xff),(currentTrack.get(j).getTick()),(n[0] & 0xff));
				notes.add(note);

				// revove note so we dont read it more than once
				currentTrack.remove(currentTrack.get(j));
			}			
		}
		// Tell sequencer to stop recording
		sequencer.stopRecording();

		// Retrieve the sequence containing the stuff you played on the MIDI instrument
		Sequence tmp = sequencer.getSequence();

		// Save to file
		try 
		{
			MidiSystem.write(tmp, 0, new File("MyMidiFile.mid"));
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

		System.out.println("done");

		inputDevice.close();
		sequencer.close();
	}*/
}
