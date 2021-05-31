package MIDI.Parsing;

public class Tie 
{
    private double  fraction;
    private int measures;

    public double getFraction()
    {
        //System.out.println("Tie: getFraction");
        return fraction;
    }
    public int getMeasures()
    {
        //System.out.println("Tie: getMeasures");
        return measures;
    }
    public Tie(int measures, double  fraction)
    {
        //System.out.println("Tie");
        this.measures = measures;
        this.fraction = fraction;
    }
    @Override
    public String toString() 
    {
        return "Measures: " + measures + " Fraction/Off: " + fraction;
    }
}