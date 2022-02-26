package Graphics.Controll;

import java.util.ArrayList;
import java.util.LinkedList;

import Graphics.SongNote;
import Graphics.Piano.Key;

import java.awt.Color;
import java.awt.Graphics;
/**handles updating and drawing of all objects*/
public class Handler 
{
    public LinkedList<Objects> objects = new LinkedList<Objects>();
    private LinkedList<Integer> pressed = new LinkedList<Integer>();
    private LinkedList<Integer> toBePressed = new LinkedList<Integer>();
    /**updates all objects */
    public void tick()
    {
        for(int i = 0; i < objects.size(); i++)//update all objects befor looking for collision
        {
            objects.get(i).tick();
        }
        for(int i = 0; i < objects.size()-1; i++)
        {
            if(objects.get(i).id == ID.note)// if object is a note check for collision
            {
                SongNote obj1 = (SongNote)objects.get(i);//type cast to note
                //if(!pressed.contains(obj1.getMidi()))// if note is already pressed dont bother checking
                //{
                    for(int j = 0; j < objects.size(); j++)
                    {
                        if(objects.get(j).id == ID.key || objects.get(j).id == ID.sharpKey)// if object is piano key
                        {
                            Key obj2 = (Key)objects.get(j);
                            if(obj1.getMidi() == obj2.getMidi())// if key is the same note
                            {
                                if(obj1.y > obj2.y && obj1.y - obj1.length < obj2.y && !obj1.getPlaying())//if note is in key and not already playing
                                {
                                    obj2.setColor(Color.GREEN);
                                    j = objects.size();
                                    obj1.setPlaying(true);
                                    toBePressed.add(obj1.getMidi()); 
                                    System.out.println(pressed + " " + obj1.getMidi());
                                    if(pressed.contains(obj1.getMidi())) 
                                    {
                                        pressed.remove(pressed.indexOf(obj1.getMidi()));
                                    }
                                    System.out.println(pressed + " " + obj1.getMidi());                                  
                                }
                                else if(obj1.y - obj1.length > obj2.y && obj1.getPlaying())// if note below keyboard
                                {
                                    obj2.setDefaultColor(); 
                                    obj1.setPlaying(false);
                                }
                            }
                        } 
                    }
                //}
            }
        }
        boolean allpressed = true;
        for(int i = 0; i < toBePressed.size(); i++)// if there are notes to be played make sure they are all pressed
        {
            if(!pressed.contains(toBePressed.get(i)))
            {
                allpressed = false;
            }
        }
        if(!allpressed)//inf notes are not all pressed pause
        {            
            SongNote.pause();
        } 
        else// if all notes are pressed clear and play
        {
            toBePressed.clear();
            SongNote.play();
        }
    }
    public void render(Graphics g, int windowWidth, int windoHeight)
    {
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).render(g, windowWidth, windoHeight);
        }
    }
    public void addObject(Objects o)
    {
        objects.add(o);
    }
    public void removeObject(Objects o)
    {
        objects.remove(o);
    }
    public void press(int midi)
    {
        pressed.add(midi);        
    }
    public void unpress(int midi)
    {
        if(pressed.indexOf(midi) >= 0)
        {
            pressed.remove(pressed.indexOf(midi));
        }
    }
}
