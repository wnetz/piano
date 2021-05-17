package Graphics.Piano;

import javafx.scene.paint.Color;

public class Key 
{
    public static final Color PRIMARY_COLOR = Color.WHITE, SECODARY_COLOR = Color.CYAN;
    protected int index, midiValue;
    protected Color color;

    public Key(int index, int midiValue) 
    {
        this.index = index;
        this.midiValue = midiValue;
        color = PRIMARY_COLOR;
    }
    
    public int getIndex() 
    {
        return index;
    }
    public int getMidiValue() 
    {
        return midiValue;
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
    public Color getColor() 
    {
        return this.color;
    }

    @Override
    public String toString()
    {
        return "Color: " + this.color + " " + this.midiValue;
    }
}