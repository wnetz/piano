package Graphics.Piano;

import javafx.scene.paint.Color;

public class Key 
{
    protected Color color;
    protected int index, midiValue;
    private static final Color PRIMARY_COLOR = Color.WHITE; 
    private static final Color SECONDARY_COLOR = Color.CYAN;    

    public Color getColor() 
    {
        //System.out.println("Key: getColor");
        return this.color;
    }
    public Color pressed(boolean pressed) 
    {
        System.out.println("Key: pressed");
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
    public int getIndex() 
    {
        System.out.println("Key: getIndex");
        return index;
    }
    public int getMidiValue() 
    {
        System.out.println("Key: getMidiValue");
        return midiValue;
    }    
    public Key(int index, int midiValue) 
    {
        //System.out.println("Key");
        this.index = index;
        this.midiValue = midiValue;
        color = PRIMARY_COLOR;
    } 
    @Override
    public String toString()
    {
        return "Color: " + this.color + " " + this.midiValue;
    }
}