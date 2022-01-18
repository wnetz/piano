package Graphics.Controll;

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler 
{
    LinkedList<Objects> objects = new LinkedList<Objects>();

    public void tick()
    {
        for(int i = 0; i < objects.size(); i++)
        {
            objects.get(i).tick();
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
