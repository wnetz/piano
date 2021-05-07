package MIDIInput;

public class Note {
    public int note;
    public int volume;
    public int onOff;
    public long time;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Note: " + note + " Volume: " + volume + " On/Off: " + onOff + " Time: " + time;
    }
}