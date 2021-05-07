package Graphics.Piano;

import java.util.ArrayList;

public class Piano {
    final static public int LOW = 21;
    final static public int KEYS = 88;
    final static public int NATURAL = 52;
    final static public int SHARP_FLAT = 36;
    final static public int ON = 144;
    final static public int[] WHIGHTKEYS = { 21, 23, 24, 26, 28, 29, 31, 33, 35, 36, 38, 40, 41, 43, 45, 47, 48, 50, 52,
            53, 55, 57, 59, 60, 62, 64, 65, 67, 69, 71, 72, 74, 76, 77, 79, 81, 83, 84, 86, 88, 89, 91, 93, 95, 96, 98,
            100, 101, 103, 105, 107, 108 };
    final static public int[] BLACKKEYS = { 22, 25, 27, 30, 32, 34, 37, 39, 42, 44, 46, 49, 51, 54, 56, 58, 61, 63, 66,
            68, 70, 73, 75, 78, 80, 82, 85, 87, 90, 92, 94, 97, 99, 102, 104, 106 };
    public Key[] whightKeys;
    public BlackKey[] blackKeys;

    public Piano() {
        whightKeys = new Key[NATURAL];
        blackKeys = new BlackKey[SHARP_FLAT];
        int count = 0;
        for (int i = 0; i < KEYS; i++) {
            int midi = i + LOW;
            if (midi % 12 == 1 || midi % 12 == 3 || midi % 12 == 6 || midi % 12 == 8 || midi % 12 == 10) {
                blackKeys[count] = new BlackKey(count, midi);
                count++;
                
            } else {
                whightKeys[i - count] = new Key(i - count, midi);
            }
        }
    }

    public void pressed(ArrayList<Integer[]> pressedNotes) {
        pressedNotes.forEach((n) -> {            
            if (n[0] % 12 == 1 || n[0] % 12 == 3 || n[0] % 12 == 6 || n[0] % 12 == 8 || n[0] % 12 == 10) {
                for (int i = 0; i < SHARP_FLAT; i++) {
                    if (this.blackKeys[i].midiValue == n[0]) {
                        this.blackKeys[i].pressed(n[1] == ON);
                        i = SHARP_FLAT;
                    }
                }
            } else {
                for (int i = 0; i < NATURAL; i++) {
                    if (this.whightKeys[i].midiValue == n[0]) {
                        this.whightKeys[i].pressed(n[1] == ON);
                        i = NATURAL;
                    }
                }
            }
        });
    }
}
