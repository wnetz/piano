package Graphics;

public class Notes {
    private int note, volume, n, d;
    private double time, duration, bps;

    public Notes(int note,int volume, int n, int d,double time, double duration, double bps)
    {
        this.note = note;
        this.volume = volume;
        this.n = n;
        this.d = d;
        this.time = time;
        this.duration = duration;
        this.bps = bps;
    }
    public int getNote()
    {
        return note;
    }
    public int getVolume()
    {
        return volume;
    }
    public int getN()
    {
        return n;
    }
    public int getD()
    {
        return d;
    }
    public double getTime()
    {
        return time;
    }
    public double getDuration()
    {
        return duration;
    }
    public double getBPS()
    {
        return bps;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "N: " + note + " T: " + time + " D: " + duration ;
    }
}
