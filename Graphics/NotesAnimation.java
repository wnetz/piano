package Graphics;

import com.jfoenix.controls.JFXSlider;
import Graphics.Piano.BlackKey;
import Graphics.Piano.Piano;
import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class NotesAnimation 
{    
    private ArrayList<Notes> notes; 
    private ArrayList<TranslateTransition> transitions; 
    private boolean pause;  
    private double pxlsPerBeat;
    private DoubleProperty height, width;  
    private Duration time;
    private final double  MEASURES_ON_SCREEN = 2;
    private JFXSlider tslider;  
    private Pane root;

    public boolean getPause()
    {
        //System.out.println("NotesAnimation: getPause");
        return pause;
    }
    public double getHeight()
    {
        System.out.println("NotesAnimation: getHeight");
        return height.get();
    }
    public double getWidth()
    {
        System.out.println("NotesAnimation: getHeight");
        return width.get();
    }
    public DoubleProperty getHeightProperty()
    {
        System.out.println("NotesAnimation: GetHeightProperty");
        return height;
    }
    public DoubleProperty getWidthProperty()
    {
        System.out.println("NotesAnimation: GetHeightProperty");
        return width;
    }
    public Duration getTime()
    {
        
        if(transitions.size() == 0)
        {
            return time;
        }
        return transitions.get(0).getCurrentTime();
    }
    public NotesAnimation(ArrayList<Notes> notes, JFXSlider tslider) 
    {
        System.out.println("NotesAnimation");
        pxlsPerBeat = 0;
        this.notes = notes;
        transitions = new ArrayList<TranslateTransition>();
        height = new SimpleDoubleProperty(this,"height",0);
        width = new SimpleDoubleProperty(this,"width",0);
        time = new Duration(0);
        root = new Pane();
        pause = false;
        this.tslider = tslider;

        ChangeListener<Number> resize = (observable,oldvalue,newvalue) -> {
            System.out.println("NotesAnimation: resize>");
            root.setPrefHeight(height.get()*4/5.0);
            root.setPrefWidth(width.get());
            this.update();
            System.out.println("NotesAnimation: resize<");
        };

        height.addListener(resize);
        width.addListener(resize);        
    }
    public Pane getPane()
    {
        System.out.println("NotesAnimation: getPane");
        return root;
    }
    public ReadOnlyObjectProperty<Duration> getTimeProperty()
    {
        System.out.println("NotesAnimation: getTimeProperty");
        return transitions.get(0).currentTimeProperty();
    }
    public void setHeight(double h)
    {
        System.out.println("NotesAnimation: setHeight");
        height.set(h);
    }
    public void setTime(Duration t)//moves notes
    {      
        System.out.println("NotesAnimation: setHeight");
        time = t;       
    }
    public void setWidth(double w)
    {
        System.out.println("NotesAnimation: setHeight");
        width.set(w);
    }
    public void update() 
    {
        System.out.println("NotesAnimation: update>");

        double height = root.getPrefHeight();
        double pxPerSec;
        double toY = (notes.get(notes.size()-1).getTime()) * height/(notes.get(notes.size()-1).getTimeSignitureN()*MEASURES_ON_SCREEN);
        double width = root.getPrefWidth();
        double widthPerNote = root.getPrefWidth()/52;
        double y; 
        if(!pause)//update time if not paused
        {
            time = this.getTime();
        }
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

            if(notes.get(i).getTime()%notes.get(i).getTimeSignitureN() == 0)//draw measure lines
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
                    if(Piano.BLACK_KEYS[j] == note)
                    {
                        index = j;
                        j = Piano.SHARP_FLAT;
                    }
                }
                
                Rectangle rect = new Rectangle(Piano.blackKeys[index].getX() * widthPerNote + widthPerNote * Piano.blackKeys[index].getMultiplier(), y - (notes.get(i).getDuration()*pxlsPerBeat), BlackKey.BLACK_KEY_WIDTH*widthPerNote, notes.get(i).getDuration()*pxlsPerBeat);
                rect.setFill(notes.get(i).getColor());
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
                    if(Piano.WHIGHT_KEYS[j] == note)
                    {
                        index = j;
                        j = Piano.NATURAL;
                    }
                }

                Rectangle rect = new Rectangle((index) * widthPerNote + BlackKey.BLACK_KEY_WIDTH/2*widthPerNote, y - (notes.get(i).getDuration()*pxlsPerBeat), (1-BlackKey.BLACK_KEY_WIDTH)*widthPerNote, notes.get(i).getDuration()*pxlsPerBeat);
                rect.setFill(notes.get(i).getColor());
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

        ChangeListener<Duration> timeUpdate = new ChangeListener<Duration>()
        {
            @Override
            public void changed(ObservableValue<? extends Duration> arg0, Duration oldvalue, Duration newvalue) 
            {
                //System.out.println("NotesAnimation: update: timeUpdate>");
                if(!pause)
                {
                    tslider.setValue(newvalue.toMinutes()); 
                }   
                //System.out.println("NotesAnimation: update: timeUpdate<");       
            }
        };
        transitions.get(0).currentTimeProperty().addListener(timeUpdate);

        if(pause)
        {
            //this.pause();
        }

        Rectangle top = new Rectangle(0,-1000,width,1000);
        top.setFill(Color.valueOf("fafa55"));//temporary color
        root.getChildren().add(top); 

        System.out.println("NotesAnimation: update<");
    }    
    public void pause()
    {
        pause = true;
        transitions.forEach((t) -> 
        {
            t.pause();;
        });
    }
    public void play()
    {
        pause = false;
        transitions.forEach((t) -> 
        {
            t.play();;
        });
    }
    
}
