package Graphics.Piano;

import java.awt.Color;

public class Key {
    private Color secondaryColor;
    private Color priamryColor;
    protected int index;
    protected int midiValue;
    protected Color color;

    public Key(int index, int midiValue) {
        priamryColor = Color.WHITE;
        secondaryColor = Color.CYAN;

        this.index = index;
        this.midiValue = midiValue;
        color = this.priamryColor;
    }

    public int getIndex() {
        return index;
    }

    public int getMidiValue() {
        return midiValue;
    }

    public Color pressed(boolean pressed) {
        if (pressed) {
            this.color = secondaryColor;
        } else {
            this.color = priamryColor;
        }
        System.out.println(this.color);
        return this.color;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString()
    {
        return "Color: " + this.color + " " + this.midiValue;
    }
}