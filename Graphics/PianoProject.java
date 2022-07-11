package Graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Graphics.Controll.Handler;
import Graphics.Controll.NotePlayedEvent;
import Graphics.Controll.NotePlayedListener;
import Graphics.Piano.Key;
import Graphics.Piano.Piano;
import Graphics.Piano.SharpKey;
import MIDI.MIDI;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;

public class PianoProject extends Canvas implements Runnable
{    
    public static final int WIDTH = 640, HEIGHT = WIDTH /12 * 9;
    public final double MENUWIDTH = 1/30.0;
    public List<NotePlayedEvent> noteListeners;
    private Thread mainThread;
    private Thread pianoThread;
    private boolean running = false;
    private Handler handler;
    private MIDI midi;
    private Parser parser;
    private Menu menu;
    private SongDisplay songDisplay;

    public PianoProject()
    {
        noteListeners = new ArrayList<>();
        parser = new Parser();
        Song song = parser.parse("C:/Users/wnetz/Documents/piano/MIDI/Parsing/Watashi_no_Uso.mscx"); 
        midi = new MIDI(this);
        handler = new Handler(); 
        songDisplay = new SongDisplay(song,handler);
        menu = new Menu(); 
        
        System.setProperty("sun.awt.noerasebackground", "true");
             
        this.addNotePlayedListener(new NotePlayedListener(handler));
        this.addMouseListener(menu);
        new MainPage(WIDTH, HEIGHT, "pp", this);
        //this.setBackground(Color.GRAY); 
        Piano piano = new Piano(this); 
        LinkedList<Key> keys = piano.getKeys();
        LinkedList<SharpKey> sharpKeys = piano.getSharpKeys(); 
        
        for(int i = 0; i < keys.size(); i++)
        {
            handler.addObject(keys.get(i));
        }
        for(int i = 0; i < sharpKeys.size(); i++)
        {
            handler.addObject(sharpKeys.get(i));
        }
    }

    public synchronized void start()
    {
        System.out.println("start");
        mainThread = new Thread(this);
        mainThread.start();
        pianoThread = new Thread(midi);
        pianoThread.start();
        running = true;
    }
    public synchronized void stop()
    {
        try
        {
            mainThread.join();
            midi.stop();
            pianoThread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        mainThread = new Thread();
        mainThread.start();
        pianoThread = new Thread();
        pianoThread.start();
    }
    @Override
    public void run() 
    {
        System.out.println("run");
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 /  amountOfTicks;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while(delta >= 1) 
                {
                    tick();
                    delta--;
                }
                if(running)
                {
                    render();
                }
                frames++;

                if(System.currentTimeMillis() - timer > 1000)
                {
                    timer += 1000;
                    //System.out.println("FPS: " + frames);
                    frames = 0;
                }
        }
        stop();
    }
    private void tick()
    {
        handler.tick();
        menu.tick((int)(this.getWidth()*MENUWIDTH), this.getHeight());
        
    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        
        g.setColor(Color.gray);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        handler.render(g,this.getWidth(), this.getHeight(),(1-MENUWIDTH));
        menu.render(g, (int)(this.getWidth()*MENUWIDTH), this.getHeight());

        //System.out.println(this.getSize());
        g.dispose();
        bs.show();
    }
    public void addNotePlayedListener(NotePlayedEvent np)
    {
        noteListeners.add(np);
    }
    public void notePlayed(int note, int state)
    {
        // 144 on 128 off
        if(state == 144)
        {
            for(NotePlayedEvent listener : noteListeners)
            {
                listener.notePressed(note);
            }
        }
        else
        {
            for(NotePlayedEvent listener : noteListeners)
            {
                listener.noteReleased(note);
            }
        }
        //System.out.println(note);
        
    }
    public static void main(String[] args) 
    {
        new PianoProject();

        /* = new MIDI();
        Scanner s = new Scanner(System.in);
        for(int i =0; i < 5; i++)
        {
            midi.getNotes();
            s.nextLine();
        }
        s.close();*/
    }      
}