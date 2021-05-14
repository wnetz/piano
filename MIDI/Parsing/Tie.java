package MIDI.Parsing;

public class Tie {
    private int measures;
    private double  fraction;

    public Tie(int measures, double  fraction)
    {
        this.measures = measures;
        this.fraction = fraction;
    }
    public int getMeasures()
    {
        return measures;
    }
    public double getFraction()
    {
        return fraction;
    }
    
    @Override
    public String toString() 
    {
        return "Measures: " + measures + " Fraction/Off: " + fraction;
    }
}