package Graphics.Piano;

import javafx.scene.paint.Color;

public class BlackKey extends Key
{
    public static final double BLACK_KEY_WIDTH = .4;
    public static final Color PRIMARY_COLOR = Color.BLACK, SECODARY_COLOR = Color.BLUE;
    protected int x;
    protected double multiplier;

    public BlackKey(int index, int midiValue)
    {
        super(index, midiValue);
        color = PRIMARY_COLOR;
        multiplier = BLACK_KEY_WIDTH*2;
        x = this.midiValue - Piano.LOW - 1 - this.index;
    }
    
    public int getX()
    {
        return x;
    }
    public double getMultiplier()
    {
        return multiplier;
    }
    
    public Color pressed(boolean pressed) 
    {
        if (pressed) 
        {
            this.color = SECODARY_COLOR;
        } 
        else 
        {
            this.color = PRIMARY_COLOR;
        }
        return this.color;
    }
}