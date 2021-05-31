package Graphics.Piano;

import javafx.scene.paint.Color;

public class BlackKey extends Key
{
    protected double multiplier;    
    protected int x;
    public static final double BLACK_KEY_WIDTH = .4;
    private static final Color PRIMARY_COLOR = Color.BLACK;
    private static final Color SECONDARY_COLOR = Color.BLUE;

    public BlackKey(int index, int midiValue)
    {        
        super(index, midiValue);
        //System.out.println("BlackKey");
        color = PRIMARY_COLOR;
        multiplier = BLACK_KEY_WIDTH*2;
        x = this.midiValue - Piano.LOW - 1 - this.index;
    }
    public Color pressed(boolean pressed) 
    {
        System.out.println("BlackKey: pressed");
        if (pressed) 
        {
            this.color = SECONDARY_COLOR;
        } 
        else 
        {
            this.color = PRIMARY_COLOR;
        }
        return this.color;
    }
    public double getMultiplier()
    {
        //System.out.println("BlackKey: getMultiplier");        
        return multiplier;
    }
    public int getX()
    {
        return x;
    }   
}