import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MIDI
{
	public static void main(String args[]) throws MidiUnavailableException, InvalidMidiDataException, IOException
	{
		Frame frame = new Frame();


		MidiDevice inputDevice;
		Receiver receiver;
		Scanner scan = new Scanner(System.in);
		Sequence seq;
		Sequencer sequencer;
		Track currentTrack;
		Transmitter transmitter;
		Dictionarys dictionary = new Dictionarys();
		int device = 5;
		
		//all MIDI devices
		for(int i=0;i<MidiSystem.getMidiDeviceInfo().length;i++)
		{
		    System.out.println(MidiSystem.getMidiDeviceInfo()[i].getName() + " - " + MidiSystem.getMidiDeviceInfo()[i].getDescription());
		}
		
		
		inputDevice = MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[device]);//piano
		sequencer = MidiSystem.getSequencer();
		
		
		// Open a connection to your input device
		inputDevice.open();
		// Open a connection to the default sequencer (as specified by MidiSystem)
		sequencer.open();
		// Get the transmitter class from your input device
		transmitter = inputDevice.getTransmitter();
		// Get the receiver class from your sequencer
		receiver = sequencer.getReceiver();
		// Bind the transmitter to the receiver so the receiver gets input from the transmitter
		transmitter.setReceiver(receiver);
		// Create a new sequence
		seq = new Sequence(Sequence.PPQ, 24);
		// And of course a track to record the input on
		currentTrack = seq.createTrack();
		// Do some sequencer settings
		sequencer.setSequence(seq);
		sequencer.setTickPosition(0);
		sequencer.recordEnable(currentTrack, -1);
		// And start recording
		sequencer.startRecording();
		
		
		
		//currentTrack.remove(currentTrack.get(0));
		// Stop recording
		scan.nextLine();
		int count = 0;
		while(count < 10)
		{
			for(int j = 0; j < currentTrack.size()-1;j+=0)
			{
				//System.out.println(currentTrack.get(j).getMessage().getStatus());
				
				byte[] n = currentTrack.get(j).getMessage().getMessage();
				//System.out.println(Arrays.toString(n));
				if((n[0]& 0xff) == 144)
				{
					System.out.print((n[0]& 0xff) + " " + (n[1]& 0xff) + " " + dictionary.getVolume((n[2]& 0xff)) + " " + (n[2]& 0xff)); 
					
					System.out.println("| " + currentTrack.get(j).getTick());

					count++;
				}
				currentTrack.remove(currentTrack.get(j));
			}
		}
	    // Tell sequencer to stop recording
	    sequencer.stopRecording();

	    // Retrieve the sequence containing the stuff you played on the MIDI instrument
	    Sequence tmp = sequencer.getSequence();

	    // Save to file
	    MidiSystem.write(tmp, 0, new File("MyMidiFile.mid"));
		
		System.out.println("done");
		scan.close();
		inputDevice.close();
		sequencer.close();
	}
	public static String[] List()
	{
		String[] list = new String[MidiSystem.getMidiDeviceInfo().length];
		for(int i=0;i<MidiSystem.getMidiDeviceInfo().length;i++)
		{
			list[i] = MidiSystem.getMidiDeviceInfo()[i].getName() + " - " + MidiSystem.getMidiDeviceInfo()[i].getDescription();
		}
		return list;
	}
}
