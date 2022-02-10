package Graphics.Controll;

import java.util.LinkedList;
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
        /*for(int i = 0; i < objects.size()-1; i++)
        {
            if(objects.get(i).id == ID.note)
            {
                Objects obj1 = objects.get(i);
                for(int j = i+1; j < objects.size(); j++)
                {
                    if(objects.get(j).id == ID.key || objects.get(j).id == ID.sharpKey)
                    {
                        Objects obj2 = objects.get(j);
                        if(obj1.x == obj2.x)
                        {
                            System.out.println(obj1.x);
                        }
                    }

                }
            }
        }*/
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
