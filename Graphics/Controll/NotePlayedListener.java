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
        for(int i = 0; i < handler.objects.size(); i++)
        {
            Objects tempObject = handler.objects.get(i);
            if(tempObject.getID() == ID.a0 && note == 21)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as0 && note == 22)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b0 && note == 23)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c1 && note == 24)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.cs1 && note == 25)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.d1 && note == 26)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.ds1 && note == 27)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.e1 && note == 28)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.f1 && note == 29)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.fs1 && note == 30)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.g1 && note == 31)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.gs1 && note == 32)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.a1 && note == 33)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as1 && note == 34)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b1 && note == 35)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c2 && note == 36)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.cs2 && note == 37)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.d2 && note == 38)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.ds2 && note == 39)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.e2 && note == 40)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.f2 && note == 41)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.fs2 && note == 42)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.g2 && note == 43)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.gs2 && note == 44)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.a2 && note == 45)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as2 && note == 46)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b2 && note == 47)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c3 && note == 48)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.cs3 && note == 49)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.d3 && note == 50)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.ds3 && note == 51)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.e3 && note == 52)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.f3 && note == 53)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.fs3 && note == 54)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.g3 && note == 55)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.gs3 && note == 56)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.a3 && note == 57)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as3 && note == 58)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b3 && note == 59)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c4 && note == 60)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.cs4 && note == 61)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.d4 && note == 62)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.ds4 && note == 63)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.e4 && note == 64)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.f4 && note == 65)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.fs4 && note == 66)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.g4 && note == 67)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.gs4 && note == 68)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.a4 && note == 69)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as4 && note == 70)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b4 && note == 71)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c5 && note == 72)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.cs5 && note == 73)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.d5 && note == 74)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.ds5 && note == 75)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.e5 && note == 76)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.f5 && note == 77)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.fs5 && note == 78)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.g5 && note == 79)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.gs5 && note == 80)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.a5 && note == 81)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as5 && note == 82)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b5 && note == 83)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c6 && note == 84)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.cs6 && note == 85)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.d6 && note == 86)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.ds6 && note == 87)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.e6 && note == 88)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.f6 && note == 89)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.fs6 && note == 90)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.g6 && note == 91)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.gs6 && note == 92)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.a6 && note == 93)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as6 && note == 94)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b6 && note == 95)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c7 && note == 96)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.cs7 && note == 97)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.d7 && note == 98)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.ds7 && note == 99)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.e7 && note == 100)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.f7 && note == 101)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.fs7 && note == 102)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.g7 && note == 103)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.gs7 && note == 104)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.a7 && note == 105)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.as7 && note == 106)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.b7 && note == 107)tempObject.setColor(Color.red);
            else if(tempObject.getID() == ID.c8 && note == 108)tempObject.setColor(Color.red);
        }
    }
    public void noteReleased(int note) 
    {        
        System.out.println("off: " + note);
        for(int i = 0; i < handler.objects.size(); i++)
        {
            Objects tempObject = handler.objects.get(i);
            if(tempObject.getID() == ID.a0 && note == 21)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as0 && note == 22)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b0 && note == 23)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c1 && note == 24)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.cs1 && note == 25)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.d1 && note == 26)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.ds1 && note == 27)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.e1 && note == 28)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.f1 && note == 29)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.fs1 && note == 30)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.g1 && note == 31)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.gs1 && note == 32)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.a1 && note == 33)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as1 && note == 34)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b1 && note == 35)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c2 && note == 36)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.cs2 && note == 37)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.d2 && note == 38)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.ds2 && note == 39)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.e2 && note == 40)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.f2 && note == 41)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.fs2 && note == 42)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.g2 && note == 43)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.gs2 && note == 44)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.a2 && note == 45)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as2 && note == 46)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b2 && note == 47)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c3 && note == 48)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.cs3 && note == 49)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.d3 && note == 50)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.ds3 && note == 51)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.e3 && note == 52)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.f3 && note == 53)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.fs3 && note == 54)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.g3 && note == 55)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.gs3 && note == 56)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.a3 && note == 57)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as3 && note == 58)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b3 && note == 59)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c4 && note == 60)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.cs4 && note == 61)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.d4 && note == 62)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.ds4 && note == 63)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.e4 && note == 64)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.f4 && note == 65)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.fs4 && note == 66)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.g4 && note == 67)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.gs4 && note == 68)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.a4 && note == 69)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as4 && note == 70)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b4 && note == 71)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c5 && note == 72)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.cs5 && note == 73)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.d5 && note == 74)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.ds5 && note == 75)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.e5 && note == 76)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.f5 && note == 77)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.fs5 && note == 78)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.g5 && note == 79)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.gs5 && note == 80)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.a5 && note == 81)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as5 && note == 82)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b5 && note == 83)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c6 && note == 84)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.cs6 && note == 85)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.d6 && note == 86)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.ds6 && note == 87)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.e6 && note == 88)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.f6 && note == 89)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.fs6 && note == 90)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.g6 && note == 91)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.gs6 && note == 92)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.a6 && note == 93)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as6 && note == 94)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b6 && note == 95)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c7 && note == 96)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.cs7 && note == 97)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.d7 && note == 98)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.ds7 && note == 99)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.e7 && note == 100)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.f7 && note == 101)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.fs7 && note == 102)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.g7 && note == 103)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.gs7 && note == 104)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.a7 && note == 105)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.as7 && note == 106)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.b7 && note == 107)tempObject.setDefaultColor();
            else if(tempObject.getID() == ID.c8 && note == 108)tempObject.setDefaultColor();
        }
    }
}