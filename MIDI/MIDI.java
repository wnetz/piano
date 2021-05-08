package MIDI;

import javax.sound.midi.*;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

public class MIDI implements Runnable {
	MidiDevice inputDevice;
	Receiver receiver;

	Sequence seq;
	Sequencer sequencer;
	Track currentTrack;
	Transmitter transmitter;
	Dictionarys dictionary;
	int device;
	ArrayList<Note> notes;
	ArrayList<Note> returnNotes;

	private boolean stopRequested = false;
	private boolean get = false;

	public MIDI() {
		dictionary = new Dictionarys();
		device = 5;

		notes = new ArrayList<Note>();
		returnNotes = new ArrayList<Note>();

		// all MIDI devices
		for (int i = 0; i < MidiSystem.getMidiDeviceInfo().length; i++) {
			System.out.println(MidiSystem.getMidiDeviceInfo()[i].getName() + " - "
					+ MidiSystem.getMidiDeviceInfo()[i].getDescription());
		}

		try {
			inputDevice = MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[device]);// piano
			sequencer = MidiSystem.getSequencer();

			// Open a connection to your input device
			inputDevice.open();
			// Open a connection to the default sequencer (as specified by MidiSystem)
			sequencer.open();
			// Get the transmitter class from your input device
			transmitter = inputDevice.getTransmitter();
			// Get the receiver class from your sequencer
			receiver = sequencer.getReceiver();
			// Bind the transmitter to the receiver so the receiver gets input from the
			// transmitter
			transmitter.setReceiver(receiver);
			// Create a new sequence
			seq = new Sequence(Sequence.PPQ, 60);
			// And of course a track to record the input on
			currentTrack = seq.createTrack();
			// Do some sequencer settings
			sequencer.setSequence(seq);
		} catch (MidiUnavailableException e) {
			System.out.println(e.getMessage());
		} catch (InvalidMidiDataException e) {
			System.out.println(e.getMessage());
		}
		sequencer.setTempoInBPM(60);
		sequencer.setTickPosition(0);
		sequencer.recordEnable(currentTrack, -1);
		// And start recording
		sequencer.startRecording();
	}

	public synchronized void requestStop() {
		stopRequested = true;
	}

	private synchronized boolean isStopRequested() {
		return this.stopRequested;
	}

	public ArrayList<Note> getNotes() {
		get = true;
		while(get)
		{
			try
			{
				Thread.sleep(1);
			}
			catch(IllegalArgumentException e)
			{
				System.out.println(e.getMessage());
			}
			catch(InterruptedException e)
			{
				System.out.println(e.getMessage());
			}
			
		}	
		return returnNotes;		
	}

	@Override
	public void run() {
		//long time = System.currentTimeMillis();
		// currentTrack.remove(currentTrack.get(0));
		// Stop recording
		while (!isStopRequested()) {			
			if(get)
			{	
				returnNotes.clear();
				notes.forEach((n)-> returnNotes.add(n));
				notes.clear();				
				get = false;			
			}
			// cycle through all unread notes
			for (int j = 0; j < currentTrack.size() - 1; j += 0) {
				// System.out.println(currentTrack.get(j).getMessage().getStatus());

				byte[] n = currentTrack.get(j).getMessage().getMessage();
				Note note = new Note((n[1] & 0xff),(n[2] & 0xff),(currentTrack.get(j).getTick()),(n[0] & 0xff));
				notes.add(note);
				System.out.println(note);
				// System.out.print(note);
				// System.out.println("| " + currentTrack.get(j).getTick() + " Diff" +
				// ((System.currentTimeMillis() - 1000*(currentTrack.get(j).getTick()/120.0))-
				// time));
				// 144 on

				// revove note so we dont read it more than once
				currentTrack.remove(currentTrack.get(j));
			}
			//if(notes.size()>0)
				//System.out.println("get " + notes + " " + System.currentTimeMillis());			
		}
		// Tell sequencer to stop recording
		sequencer.stopRecording();

		// Retrieve the sequence containing the stuff you played on the MIDI instrument
		Sequence tmp = sequencer.getSequence();

		// Save to file
		try {
			MidiSystem.write(tmp, 0, new File("MyMidiFile.mid"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("done");

		inputDevice.close();
		sequencer.close();
	}

	public static String[] List() {
		String[] list = new String[MidiSystem.getMidiDeviceInfo().length];
		for (int i = 0; i < MidiSystem.getMidiDeviceInfo().length; i++) {
			list[i] = MidiSystem.getMidiDeviceInfo()[i].getName() + " - "
					+ MidiSystem.getMidiDeviceInfo()[i].getDescription();
		}
		return list;
	}
}
