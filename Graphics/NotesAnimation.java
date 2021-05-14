package Graphics;

import Graphics.Piano.BlackKey;
import Graphics.Piano.Key;
import Graphics.Piano.Piano;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class NotesAnimation 
{    
    private final double  measursOnScreen = 2;    
    private double height;//pxls per beat
    private long currentTime, zeroTime;
    private ArrayList<Notes> notes;

    public NotesAnimation(ArrayList<Notes> notes) 
    {
        this.currentTime = System.currentTimeMillis();        
        this.zeroTime = System.currentTimeMillis();
        this.notes = notes;
    }

    public void paintComponent(double windowHeight, double windowWidth, Graphics2D g2d) 
    {
        double time = (currentTime - zeroTime)/1000.0;
        double pxPerSec;
        double width = windowWidth/52; 
        double y;     

        for(int i = 0; i < notes.size(); i++)//loop on notes in song
        {     
            int note = notes.get(i).getNote(); 

            height = windowHeight/(notes.get(i).getTimeSignitureN()*measursOnScreen);
            pxPerSec = height*notes.get(i).getBPS();              
            y = pxPerSec * time - pxPerSec * notes.get(i).getTime(); 

            if(y >= 0 && y - (notes.get(i).getDuration()*pxPerSec) < windowHeight )//if note is not on screen dont bother with it   
            {        
                if (note % 12 == 1 || note % 12 == 3 || note % 12 == 6 || note % 12 == 8 || note % 12 == 10)//if sharp or flat
                {                    
                    int index = 0;
                    for(int j = 0; j < Piano.SHARP_FLAT; j++)//find index of note in black keys in order to paint it
                    {
                        if(Piano.BLACKKEYS[j] == note)
                        {
                            index = j;
                            j = Piano.SHARP_FLAT;
                        }
                    }

                    g2d.setColor(BlackKey.SECODARY_COLOR);                    
                    g2d.fillRoundRect((int) Math.round(Piano.blackKeys[index].getX() * width + width * Piano.blackKeys[index].getMultiplier()), (int)(y - (notes.get(i).getDuration()*pxPerSec)), (int) (width / 2), (int) (notes.get(i).getDuration()*pxPerSec),15,15);
                }
                else
                {                    
                    int index = 0;
                    for(int j = 0; j < Piano.NATURAL; j++)//find index of note in whight keys in order to paint it
                    {
                        if(Piano.WHIGHTKEYS[j] == note)
                        {
                            index = j;
                            j = Piano.NATURAL;
                        }
                    }

                    g2d.setColor(Key.SECODARY_COLOR);
                    g2d.fillRoundRect((int) Math.round((index) * width), (int)(y - (notes.get(i).getDuration()*pxPerSec)), (int) width, (int) (notes.get(i).getDuration()*pxPerSec),15,15);
                } 
            }        
        }
    }    
    public void setTime()//moves notes
    {
        currentTime = System.currentTimeMillis();             
    }
}
