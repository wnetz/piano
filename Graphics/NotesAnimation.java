package Graphics;

import Graphics.Piano.BlackKey;
import Graphics.Piano.Key;
import Graphics.Piano.Piano;
import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class NotesAnimation 
{    
    private final double  MEASURES_ON_SCREEN = 2;    
    private double pxlsPerBeat;//pxls per beat
    private ArrayList<Notes> notes; 
    private ArrayList<TranslateTransition> transitions; 
    private DoubleProperty height, width;  
    private Duration time; 
    private Pane root;

    public NotesAnimation(ArrayList<Notes> notes) 
    {
        pxlsPerBeat = 0;
        this.notes = notes;
        transitions = new ArrayList<TranslateTransition>();
        height = new SimpleDoubleProperty(this,"height",0);
        width = new SimpleDoubleProperty(this,"width",0);
        time = new Duration(0);
        root = new Pane();

        ChangeListener<Number> resize = (observable,oldvalue,newvalue) -> {
            root.setPrefHeight(height.get()*4/5.0);
            root.setPrefWidth(width.get());
            this.update();
        };

        height.addListener(resize);
        width.addListener(resize);         
    }
    
    public DoubleProperty heightProperty()
    {
        return height;
    }
    public DoubleProperty widthProperty()
    {
        return width;
    }
    public double getHeight()
    {
        return height.get();
    }
    public double getWidth()
    {
        return width.get();
    }
    public Duration getTime()
    {
        if(transitions.size() == 0)
        {
            return time;
        }
        return transitions.get(0).getCurrentTime();
    }
    public Pane getPane()
    {
        return root;
    }

    public void setHeight(double h)
    {
        height.set(h);
    }
    public void setWidth(double w)
    {
        width.set(w);
    }
    public void setTime(Duration t)//moves notes
    {      
        time = t;       
    }

    public void update() 
    {
        double height = root.getPrefHeight();
        double pxPerSec;
        double toY = (notes.get(notes.size()-1).getTime()) * height/(notes.get(notes.size()-1).getTimeSignitureN()*MEASURES_ON_SCREEN);
        double width = root.getPrefWidth();
        double widthPerNote = root.getPrefWidth()/52;
        double y; 

        time = this.getTime();
        transitions.forEach(t ->
        {
            t.stop();
        });
        transitions.clear();        
        
         
        Rectangle background = new Rectangle(0,0,width,height);
        background.setFill(Color.GRAY);
        root.getChildren().add(background);        

        for(int i = 0; i < notes.size(); i++)//loop on notes in song
        {     
            int note = notes.get(i).getNote(); 

            pxlsPerBeat = height / (notes.get(i).getTimeSignitureN()*MEASURES_ON_SCREEN);
            pxPerSec = pxlsPerBeat * notes.get(i).getBPS();         
            y = - pxlsPerBeat * notes.get(i).getTime();

            TranslateTransition trans = new TranslateTransition();
            transitions.add(trans);
            trans.setDuration(Duration.seconds((toY + height) / pxPerSec));
            trans.setToY(toY + height);
            trans.setInterpolator(Interpolator.LINEAR);

            if(notes.get(i).getTime()%notes.get(i).getTimeSignitureN() == 0)
            {
                Line measureLine = new Line(0, y, width, y);
                measureLine.setFill(Color.DARKGRAY);                                
                root.getChildren().add(measureLine);

                TranslateTransition t = new TranslateTransition();
                transitions.add(t);
                t.setDuration(Duration.seconds((toY + height) / pxPerSec));
                t.setToY(toY + height);
                t.setInterpolator(Interpolator.LINEAR);                
                t.setNode(measureLine);
            }      
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
                
                Rectangle rect = new Rectangle(Piano.blackKeys[index].getX() * widthPerNote + widthPerNote * Piano.blackKeys[index].getMultiplier(), y - (notes.get(i).getDuration()*pxlsPerBeat), BlackKey.BLACK_KEY_WIDTH*widthPerNote, notes.get(i).getDuration()*pxlsPerBeat);
                rect.setFill(BlackKey.SECODARY_COLOR);
                rect.setArcHeight((BlackKey.BLACK_KEY_WIDTH*widthPerNote)*(3/4.0));
                rect.setArcWidth((BlackKey.BLACK_KEY_WIDTH*widthPerNote)*(3/4.0));

                trans.setNode(rect);
                root.getChildren().add(rect);
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

                Rectangle rect = new Rectangle((index) * widthPerNote + BlackKey.BLACK_KEY_WIDTH/2*widthPerNote, y - (notes.get(i).getDuration()*pxlsPerBeat), (1-BlackKey.BLACK_KEY_WIDTH)*widthPerNote, notes.get(i).getDuration()*pxlsPerBeat);
                rect.setFill(Key.SECODARY_COLOR);
                rect.setArcHeight(((1-BlackKey.BLACK_KEY_WIDTH)*widthPerNote)*(3/4.0));
                rect.setArcWidth(((1-BlackKey.BLACK_KEY_WIDTH)*widthPerNote)*(3/4.0));

                trans.setNode(rect);
                root.getChildren().add(rect);
            }     
        }
        transitions.forEach((t) -> 
        {
            t.playFrom(time);
        });
    }    
       
    public void puse()
    {
        transitions.forEach((t) -> 
        {
            t.pause();;
        });
    }
    
}
