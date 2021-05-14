package Graphics;


import javax.swing.*;

import Graphics.Piano.Piano;



import java.util.ArrayList;
import java.awt.Graphics2D;

public class NotesAnimation 
{
    
    ArrayList<Notes> notes;
    long zeroTime, currentTime;
    Graphics2D g2d;
    double height, width;
    int y;
    int numerator;
    private final double  measursOnScreen = 2;

    public NotesAnimation(ArrayList<Notes> notes) 
    {
        y = 0;
        this.notes = notes;
        this.zeroTime = System.currentTimeMillis();
        this.currentTime = System.currentTimeMillis();
    }

    public void paintComponent(Graphics2D g2d, double windowHeight, double windowWidth) 
    {
        y = 0;
        double time = (currentTime - zeroTime)/1000.0;
        double width = windowWidth/52;    
        double pxPerSec; 
        double y;     
        for(int i = 0; i < notes.size(); i++)
        {
            
            height = windowHeight/(notes.get(i).getN()*measursOnScreen);
            pxPerSec = height*notes.get(i).getBPS();
            int note = notes.get(i).getNote();   
            y = pxPerSec * time - pxPerSec * notes.get(i).getTime(); 
            if(y >= 0 && y - (notes.get(i).getDuration()*pxPerSec) < windowHeight )   
            {        
                if (note % 12 == 1 || note % 12 == 3 || note % 12 == 6 || note % 12 == 8 || note % 12 == 10)
                {
                    g2d.setColor(Piano.blackKeys[0].secondaryColor);
                    int index = 0;
                    for(int j = 0; j < Piano.SHARP_FLAT; j++)
                    {
                        if(Piano.BLACKKEYS[j] == note)
                        {
                            index = j;
                            j = Piano.SHARP_FLAT;
                        }
                    }
                    
                    g2d.fillRoundRect((int) Math.round(Piano.blackKeys[index].getX() * width + width * Piano.blackKeys[index].getMultiplier()), (int)(y - (notes.get(i).getDuration()*pxPerSec)), (int) (width / 2), (int) (notes.get(i).getDuration()*pxPerSec),15,15);
                }
                else
                {
                    g2d.setColor(Piano.whightKeys[0].secondaryColor);
                    int index = 0;
                    for(int j = 0; j < Piano.NATURAL; j++)
                    {
                        if(Piano.WHIGHTKEYS[j] == note)
                        {
                            index = j;
                            j = Piano.NATURAL;
                        }
                    }
                    g2d.fillRoundRect((int) Math.round((index) * width), (int)(y - (notes.get(i).getDuration()*pxPerSec)), (int) width, (int) (notes.get(i).getDuration()*pxPerSec),15,15);
                    //System.out.println(i + " " + time + " " + notes.get(i).getTime() + " " + (notes.get(i).getDuration()/notes.get(i).getN())+ " " + height + " " + (int)(time * notes.get(i).getTime() - ((notes.get(i).getDuration()/notes.get(i).getN())*height)));
                } 
            }        
        }
    }    

    public void setTime()
    {
        currentTime = System.currentTimeMillis();             
    }
}
