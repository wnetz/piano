package Graphics.Controll;

import java.awt.Color;

import javax.lang.model.util.ElementScanner6;

import Graphics.Piano.Key;

public class NotePlayedListener implements NotePlayedEvent
{
    private Handler handler;
    
    public NotePlayedListener(Handler handler)
    {
        this.handler = handler;
    }
    @Override
    public void notePressed(int note) 
    {        
        System.out.println("on: " + note);
        handler.press(note);
        for(int i = 0; i < handler.objects.size(); i++)
        {            
            Objects tempObject = handler.objects.get(i);
            /*if(tempObject instanceof SongNote)
            {
                if(note == ((SongNote)tempObject).getMidi() && ((SongNote)tempObject).getPlaying())
                {
                    System.out.println(((SongNote)tempObject).getMidi());
                    SongNote.play();
                }
            }*/
            if(tempObject instanceof Key)
            {
                Key tempKey = (Key)tempObject;
                if(tempKey.getNoteId() == ID.a0 && note == 21)tempObject.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as0 && note == 22)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b0 && note == 23)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c1 && note == 24)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.cs1 && note == 25)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.d1 && note == 26)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.ds1 && note == 27)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.e1 && note == 28)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.f1 && note == 29)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.fs1 && note == 30)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.g1 && note == 31)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.gs1 && note == 32)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.a1 && note == 33)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as1 && note == 34)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b1 && note == 35)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c2 && note == 36)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.cs2 && note == 37)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.d2 && note == 38)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.ds2 && note == 39)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.e2 && note == 40)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.f2 && note == 41)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.fs2 && note == 42)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.g2 && note == 43)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.gs2 && note == 44)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.a2 && note == 45)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as2 && note == 46)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b2 && note == 47)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c3 && note == 48)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.cs3 && note == 49)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.d3 && note == 50)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.ds3 && note == 51)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.e3 && note == 52)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.f3 && note == 53)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.fs3 && note == 54)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.g3 && note == 55)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.gs3 && note == 56)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.a3 && note == 57)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as3 && note == 58)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b3 && note == 59)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c4 && note == 60)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.cs4 && note == 61)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.d4 && note == 62)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.ds4 && note == 63)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.e4 && note == 64)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.f4 && note == 65)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.fs4 && note == 66)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.g4 && note == 67)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.gs4 && note == 68)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.a4 && note == 69)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as4 && note == 70)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b4 && note == 71)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c5 && note == 72)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.cs5 && note == 73)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.d5 && note == 74)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.ds5 && note == 75)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.e5 && note == 76)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.f5 && note == 77)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.fs5 && note == 78)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.g5 && note == 79)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.gs5 && note == 80)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.a5 && note == 81)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as5 && note == 82)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b5 && note == 83)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c6 && note == 84)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.cs6 && note == 85)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.d6 && note == 86)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.ds6 && note == 87)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.e6 && note == 88)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.f6 && note == 89)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.fs6 && note == 90)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.g6 && note == 91)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.gs6 && note == 92)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.a6 && note == 93)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as6 && note == 94)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b6 && note == 95)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c7 && note == 96)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.cs7 && note == 97)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.d7 && note == 98)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.ds7 && note == 99)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.e7 && note == 100)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.f7 && note == 101)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.fs7 && note == 102)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.g7 && note == 103)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.gs7 && note == 104)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.a7 && note == 105)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.as7 && note == 106)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.b7 && note == 107)tempKey.setColor(Color.red);
                else if(tempKey.getNoteId() == ID.c8 && note == 108)tempObject.setColor(Color.red);
            }
        }
    }
    public void noteReleased(int note) 
    {        
        System.out.println("off: " + note);
        handler.unpress(note);
        for(int i = 0; i < handler.objects.size(); i++)
        {
            Objects tempObject = handler.objects.get(i);
            if(tempObject instanceof Key)
            {
                Key tempKey = (Key)tempObject;
                if(tempKey.getNoteId() == ID.a0 && note == 21)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as0 && note == 22)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b0 && note == 23)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c1 && note == 24)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.cs1 && note == 25)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.d1 && note == 26)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.ds1 && note == 27)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.e1 && note == 28)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.f1 && note == 29)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.fs1 && note == 30)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.g1 && note == 31)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.gs1 && note == 32)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.a1 && note == 33)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as1 && note == 34)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b1 && note == 35)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c2 && note == 36)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.cs2 && note == 37)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.d2 && note == 38)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.ds2 && note == 39)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.e2 && note == 40)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.f2 && note == 41)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.fs2 && note == 42)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.g2 && note == 43)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.gs2 && note == 44)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.a2 && note == 45)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as2 && note == 46)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b2 && note == 47)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c3 && note == 48)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.cs3 && note == 49)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.d3 && note == 50)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.ds3 && note == 51)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.e3 && note == 52)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.f3 && note == 53)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.fs3 && note == 54)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.g3 && note == 55)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.gs3 && note == 56)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.a3 && note == 57)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as3 && note == 58)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b3 && note == 59)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c4 && note == 60)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.cs4 && note == 61)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.d4 && note == 62)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.ds4 && note == 63)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.e4 && note == 64)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.f4 && note == 65)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.fs4 && note == 66)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.g4 && note == 67)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.gs4 && note == 68)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.a4 && note == 69)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as4 && note == 70)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b4 && note == 71)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c5 && note == 72)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.cs5 && note == 73)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.d5 && note == 74)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.ds5 && note == 75)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.e5 && note == 76)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.f5 && note == 77)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.fs5 && note == 78)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.g5 && note == 79)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.gs5 && note == 80)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.a5 && note == 81)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as5 && note == 82)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b5 && note == 83)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c6 && note == 84)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.cs6 && note == 85)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.d6 && note == 86)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.ds6 && note == 87)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.e6 && note == 88)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.f6 && note == 89)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.fs6 && note == 90)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.g6 && note == 91)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.gs6 && note == 92)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.a6 && note == 93)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as6 && note == 94)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b6 && note == 95)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c7 && note == 96)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.cs7 && note == 97)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.d7 && note == 98)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.ds7 && note == 99)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.e7 && note == 100)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.f7 && note == 101)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.fs7 && note == 102)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.g7 && note == 103)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.gs7 && note == 104)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.a7 && note == 105)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.as7 && note == 106)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.b7 && note == 107)tempKey.setDefaultColor();
                else if(tempKey.getNoteId() == ID.c8 && note == 108)tempKey.setDefaultColor();
            }
        }
    }
}