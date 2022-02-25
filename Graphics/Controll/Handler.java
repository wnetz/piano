package Graphics.Controll;

import java.util.LinkedList;

import Graphics.SongNote;
import Graphics.Piano.Key;

import java.awt.Color;
import java.awt.Graphics;

public class Handler 
{
    public LinkedList<Objects> objects = new LinkedList<Objects>();

    public void tick()
    {
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).tick();
        }
        for(int i = 0; i < objects.size()-1; i++)
        {
            if(objects.get(i).id == ID.note)
            {
                SongNote obj1 = (SongNote)objects.get(i);
                for(int j = 0; j < objects.size(); j++)
                {
                    if(objects.get(j).id == ID.key || objects.get(j).id == ID.sharpKey)
                    {
                        Key obj2 = (Key)objects.get(j);
                        if(obj1.getMidi() == obj2.getMidi())
                        {
                            if(obj1.y > obj2.y && obj1.y - obj1.length < obj2.y && !obj1.getPlaying())
                            {
                                obj2.setColor(Color.GREEN);
                                j = objects.size();
                                obj1.setPlaying(true);
                                obj1.pause();
                            }
                            else if(obj1.y - obj1.length > obj2.y && obj1.getPlaying())
                            {
                                obj2.setDefaultColor(); 
                                obj1.setPlaying(false);
                            }
                        }
                    } 
                }
            }
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
}
