package Graphics;

import com.jfoenix.controls.JFXSlider;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SongPage
{
    private DoubleProperty height, width;
    private Keyboard keyboard;
    private NotesAnimation notesAnimation;    
    private VBox songpage;
    public static final double PERCENT_HEIGHT = 9/10.0;

    public DoubleProperty getHeightProperty()
    {
        System.out.println("SongPage: getHeightProperty");
        return height;
    }
    public DoubleProperty getWidthProperty()
    {
        System.out.println("SongPage: getWidthProperty");
        return width;
    }
    public double getHeight()
    {
        System.out.println("SongPage: getHeight");
        return height.get();
    }
    public double getWidth()
    {
        System.out.println("SongPage: getWidth");
        return width.get();
    }
    public NotesAnimation getAnimation()
    {
        System.out.println("SongPage: getAnimation");
        return notesAnimation;
    }
    public ReadOnlyObjectProperty<Duration> getTimeProperty()
    {
        System.out.println("SongPage: getTimeProperty");
        return notesAnimation.getTimeProperty();
    }
    public SongPage(ArrayList<Notes> notes, JFXSlider time)
    {      
        System.out.println("SongPage");     
        height = new SimpleDoubleProperty(this,"height",0);
        width = new SimpleDoubleProperty(this,"width",0);
        keyboard = new Keyboard();
        notesAnimation = new NotesAnimation(notes, time);
        songpage = new VBox();

        //auto adjust size
        keyboard.getHeightProperty().bind(songpage.prefHeightProperty());
        keyboard.getWidthProperty().bind(songpage.prefWidthProperty());
        notesAnimation.getHeightProperty().bind(songpage.prefHeightProperty());
        notesAnimation.getWidthProperty().bind(songpage.prefWidthProperty());

        songpage.getChildren().addAll(notesAnimation.getPane(),keyboard.getPane());
        
        //initial drawing
        keyboard.update();
        notesAnimation.update();

        //update on resize
        ChangeListener<Number> resize = (observable,oldvalue,newvalue) -> {
            songpage.setPrefHeight(height.get()*PERCENT_HEIGHT);
            songpage.setPrefWidth(width.get());
            this.update();
        };
        height.addListener(resize);
        width.addListener(resize);
    }
    public VBox getVbox() 
    {
        System.out.println("SongPage: getVbox");
        return songpage;
    } 
    public void pause()
    {
        System.out.println("SongPage: pause");
        notesAnimation.pause();
    }  
    public void play()
    {
        System.out.println("SongPage: play");
        notesAnimation.play();
    }
    public void setHeight(double h)
    {
        System.out.println("SongPage: setHeight");
        height.set(h);
    }
    public void setWidth(double w)
    {
        System.out.println("SongPage: setWidth");
        width.set(w);
    }    
    public void update()
    {
        System.out.println("SongPage: update>");
        keyboard.update();
        notesAnimation.update();
        System.out.println("SongPage: update<");
    }
}