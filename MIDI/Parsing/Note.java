package MIDI.Parsing;

public class Note {
    private int note, volume, onOff;
    private long time;

    public Note(int note,int volume, long time, int onOff)
    {
        this.note = note;
        this.volume = volume;
        this.time = time;
        this.onOff = onOff;
    }
    public int getNote()
    {
        return note;
    }
    public int getVolume()
    {
        return volume;
    }
    public long getTime()
    {
        return time;
    }
    public int getOnOff()
    {
        return onOff;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Note: " + note + " Volume: " + volume + " On/Off: " + onOff + " Time: " + time;
    }
}