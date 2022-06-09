package Graphics.Controll;

import java.util.ArrayList;

import Graphics.MeasureBar;
import Graphics.SongNote;
import Graphics.Piano.Key;

import java.awt.Color;
import java.awt.Graphics;
/**handles updating and drawing of all objects*/
public class Handler 
{
    public ArrayList<Objects> objects = new ArrayList<Objects>();
    private ArrayList<Integer> pressed = new ArrayList<Integer>();
    private ArrayList<Integer> toBePressed = new ArrayList<Integer>();
    /**updates all objects */
    public void tick()
    {
        for(int i = 0; i < objects.size(); i++)//update all objects befor looking for collision
        {
            objects.get(i).tick();
        }
        for(int i = 0; i < objects.size()-1; i++)
        {
            if(objects.get(i).id == ID.note || objects.get(i).id == ID.sharpNote)// if object is a note check for collision
            {
                SongNote note = (SongNote)objects.get(i);//type cast to note
                //if(!pressed.contains(note.getMidi()))// if note is already pressed dont bother checking
                //{
                    for(int j = 0; j < objects.size(); j++)
                    {
                        if(objects.get(j).id == ID.key || objects.get(j).id == ID.sharpKey)// if object is piano key
                        {
                            Key key = (Key)objects.get(j);
                            if(note.getMidi() == key.getMidi())// if key is the same note
                            {
                                if(note.y > key.y && note.y - note.length < key.y && !note.getPlaying())//if note is in key and not already playing
                                {
                                    key.setColor(Color.GREEN);
                                    j = objects.size();
                                    note.setPlaying(true);
                                    toBePressed.add(note.getMidi()); 
                                    System.out.println(pressed + " " + note.getMidi());
                                    if(pressed.contains(note.getMidi())) 
                                    {
                                        pressed.remove(pressed.indexOf(note.getMidi()));
                                    }
                                    System.out.println(pressed + " " + note.getMidi());                                  
                                }
                                else if(note.y - note.length > key.y && note.getPlaying())// if note below keyboard
                                {
                                    key.setDefaultColor(); 
                                    note.setPlaying(false);
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
            MeasureBar.pause();
        } 
        else// if all notes are pressed clear and play
        {
            toBePressed.clear();
            SongNote.play();
            MeasureBar.play();
        }
    }
    public void render(Graphics g, int windowWidth, int windoHeight)
    {
        for(int i = 0; i < objects.size(); i++)
        {
            if(objects.get(i).y+objects.get(i).length > 0 && objects.get(i).y < windoHeight*2)
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
