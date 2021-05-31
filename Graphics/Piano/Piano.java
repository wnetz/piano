package Graphics.Piano;

import java.util.ArrayList;

public class Piano 
{
    public static BlackKey[] blackKeys;
    public static final int KEYS = 88;
    public static final int LOW = 21;           //lowest MIDI value on keybord
    public static final int NATURAL = 52;       //number of whight keys
    public static final int ON = 144;           //value MIDI gives when key is pressed down
    public static final int SHARP_FLAT = 36;    //number of black keys
    public static final int[] BLACK_KEYS =  {22, 25, 27, 30, 32, 34, 37, 39, 42, 44, 46, 49, 51, 54, 56, 58, 61, 63, 66, 68, 70, 73, 75, 78, 80, 82, 85, 87, 90, 92, 94, 97, 99,102,104,106};
    public static final int[] WHIGHT_KEYS = {21, 23, 24, 26, 28, 29, 31, 33, 35, 36, 38, 40, 41, 43, 45, 47, 48, 50, 52, 53, 55, 57, 59, 60, 62, 64, 65, 67, 69, 71, 72, 74, 76, 77, 79, 81, 83, 84, 86, 88, 89, 91, 93, 95, 96, 98,100,101,103,105,107,108};
    public static Key[] whightKeys;

    public Piano() 
    {
        System.out.println("Piano");
        whightKeys = new Key[NATURAL];
        blackKeys = new BlackKey[SHARP_FLAT];

        int count = 0;
        for (int index = 0; index < KEYS; index++) 
        {
            int midi = index + LOW;
            if (midi % 12 == 1 || midi % 12 == 3 || midi % 12 == 6 || midi % 12 == 8 || midi % 12 == 10)//if Sharp or flat 
            {
                blackKeys[count] = new BlackKey(count, midi);
                count++;
                
            } 
            else 
            {
                whightKeys[index - count] = new Key(index - count, midi);//subtract the number of black keys that have passed from index
            }
        }
    }

    public void pressed(ArrayList<Integer[]> pressedNotes) 
    {
        System.out.println("Piano: pressed>");
        pressedNotes.forEach((note) -> // for each note in pressedNotes update its color
        {            
            if (note[0] % 12 == 1 || note[0] % 12 == 3 || note[0] % 12 == 6 || note[0] % 12 == 8 || note[0] % 12 == 10)// if note is sharp or flat
            {
                for (int index = 0; index < SHARP_FLAT; index++)//loop on black keys 
                {
                    if (Piano.blackKeys[index].midiValue == note[0]) 
                    {
                        Piano.blackKeys[index].pressed(note[1] == ON);//update color
                        index = SHARP_FLAT;//end loop to save time
                    }
                }
            } 
            else 
            {
                for (int index = 0; index < NATURAL; index++) //loop on whight keys
                {
                    if (Piano.whightKeys[index].midiValue == note[0]) 
                    {
                        Piano.whightKeys[index].pressed(note[1] == ON);//update color
                        index = NATURAL;//end loop to save time
                    }
                }
            }
        });
        System.out.println("Piano: pressed<");
    }
}
