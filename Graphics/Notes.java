package Graphics;

public class Notes {
    private int dynamic, note, timeSignitureD, timeSignitureN;
    private double bps, duration, time;

    public Notes(int dynamic, int note, int timeSignitureD, int timeSignitureN, double bps, double duration, double time )
    {
        this.timeSignitureD = timeSignitureD;
        this.note = note;
        this.timeSignitureN = timeSignitureN;
        this.dynamic = dynamic;
        this.bps = bps;
        this.duration = duration;
                this.time = time;
    }
    
    public int getDynamic()
    {
        return dynamic;
    }
    public int getNote()
    {
        return note;
    }
    public int getTimeSignitureD()
    {
        return timeSignitureD;
    }
    public int getTimeSignitureN()
    {
        return timeSignitureN;
    }
    public double getBPS()
    {
        return bps;
    }
    public double getDuration()
    {
        return duration;
    }
    public double getTime()
    {
        return time;
    }
    
    public void addDuration(double duration)
    {
        this.duration += duration;
    }
    
    @Override
    public String toString() 
    {
        // TODO Auto-generated method stub
        return "N: " + note + " T: " + time + " D: " + duration ;
    }
}
