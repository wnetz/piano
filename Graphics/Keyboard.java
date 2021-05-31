package Graphics;

import Graphics.Piano.BlackKey;
import Graphics.Piano.Piano;
import MIDI.MIDI;
import MIDI.Parsing.Note;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.WorkerStateEvent;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Keyboard 
{    
    private DoubleProperty height, width;
    private Pane root;

    public DoubleProperty getHeightProperty()
    {
        System.out.println("Keyboard: heightProperty");
        return height;
    }
    public DoubleProperty getWidthProperty()
    {
        System.out.println("Keyboard: widthProperty");
        return width;
    }
    public double getHeight()
    {
        System.out.println("Keyboard: getHeight");
        return height.get();
    }
    public double getWidth()
    {
        System.out.println("Keyboard: getWidth");
        return width.get();
    }
    public Keyboard() 
    {        
        System.out.println("Keyboard");
        height = new SimpleDoubleProperty(this,"height",0);
        width = new SimpleDoubleProperty(this,"width",0);
        root = new Pane();

        ArrayList<Integer[]> pressed = new ArrayList<Integer[]>();
        final MIDI service = new MIDI();
        Piano piano = new Piano();

        service.setRestartOnFailure(true);
        service.start();

        ChangeListener<Boolean> run = (observable,oldvalue,newvalue) -> 
        {
            System.out.println("Keyboard: run>");
            if(oldvalue)
            {
                ArrayList<Note> note = service.getNotes();
                if(note.size()!=0)
                {
                    System.out.println(note);
                    note.forEach((n) -> 
                    {
                        pressed.add(new Integer[]{n.getNote(),n.getOnOff()});
                    });
                    piano.pressed(pressed);
                    this.update();
                    pressed.clear();
                }                
                service.done();
                System.out.println("Keyboard: run<");
            }
            
        };
        ChangeListener<Number> resize = (observable,oldvalue,newvalue) -> 
        {
            System.out.println("Keyboard: resize>");
            root.setPrefHeight(height.get()/5.0);
            root.setPrefWidth(width.get());
            if(service.getState() == State.SCHEDULED)
            {
                try
                {
                    //service.start();
                }
                catch(IllegalStateException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            this.update();
            System.out.println("Keyboard: resize<");
        };
        ChangeListener<State> st = (observable,oldvalue,newvalue) -> 
        {
            System.out.println("Keyboard: heightProperty");
            //System.out.println(service.getState());
        };
        ChangeListener<EventHandler<WorkerStateEvent>> s = (observable,oldvalue,newvalue) -> 
        {
            System.out.println("Keyboard: heightProperty");
            //System.out.println("s");
        };
        
        service.runningProperty().addListener(run);
        service.stateProperty().addListener(st);
        service.onScheduledProperty().addListener(s);
        height.addListener(resize);
        width.addListener(resize);
    }
    public Pane getPane()
    {
        System.out.println("Keyboard: getPane");
        return root;
    }
    public void setHeight(double h)
    {
        System.out.println("Keyboard: setHeight");
        height.set(h);
    }
    public void setWidth(double w)
    {
        System.out.println("Keyboard: setWidth");
        width.set(w);
    }
    public void update()
    {
        System.out.println("Keyboard: update>");
        double height = root.getPrefHeight();
        double width = root.getPrefWidth() / 52.0;

        root.getChildren().clear();

        Rectangle bottom = new Rectangle(0,height,root.getPrefWidth(),1000);
        bottom.setFill(Color.valueOf("fafa55"));
        
        for (int i = 0; i < Piano.NATURAL; i++)//loop on whight notes paint before black keys
        {
            Rectangle key = new Rectangle(i * width, 0, width, height);
            key.setFill(Piano.whightKeys[i].getColor());
            Line line = new Line(i * width,0,i * width,root.getPrefHeight());
            line.setFill(Color.BLACK);
            root.getChildren().addAll(key,line);
        }
        
        for (int i = 0; i < Piano.SHARP_FLAT; i++)//loop on black keys
        {
            Rectangle key = new Rectangle(Piano.blackKeys[i].getX() * width + width * Piano.blackKeys[i].getMultiplier(),0, width * BlackKey.BLACK_KEY_WIDTH, height * .7);
            key.setFill(Piano.blackKeys[i].getColor());
            root.getChildren().add(key);   
        }

        root.getChildren().add(bottom);
        System.out.println("Keyboard: update<");
    } 
}
