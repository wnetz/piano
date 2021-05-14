package Graphics.Piano;

import java.awt.Color;

public class BlackKey extends Key
{
    public final Color secondaryColor;
    public final Color priamryColor;
    protected double multiplier;
    protected int x;

    public BlackKey(int index, int midiValue)
    {
        super(index, midiValue);

        secondaryColor = Color.BLUE;
        priamryColor = Color.BLACK;
        color = this.priamryColor;

        multiplier = .75;
        if (this.midiValue % 12 == 1 || this.midiValue % 12 == 6) {
            multiplier = .65;
        } else if (this.midiValue % 12 == 3 || this.midiValue % 12 == 10) {
            multiplier = .85;
        }

        this.x = this.midiValue - Piano.LOW - 1 - this.index;
    }
    public int getX()
    {
        return x;
    }
    public double getMultiplier()
    {
        return multiplier;
    }
    public Color pressed(boolean pressed) {
        if (pressed) {
            this.color = this.secondaryColor;
        } else {
            this.color = this.priamryColor;
        }
        return this.color;
    }
}