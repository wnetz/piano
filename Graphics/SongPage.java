package Graphics;

import java.util.ArrayList;

import com.jfoenix.controls.JFXSlider;

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

    public static double PERCENT_HEIGHT = 9/10.0;

    public SongPage(ArrayList<Notes> notes, JFXSlider time)
    {           
        height = new SimpleDoubleProperty(this,"height",0);
        width = new SimpleDoubleProperty(this,"width",0);
        keyboard = new Keyboard();
        notesAnimation = new NotesAnimation(notes, time);
        songpage = new VBox();

        //auto adjust size
        keyboard.heightProperty().bind(songpage.prefHeightProperty());
        keyboard.widthProperty().bind(songpage.prefWidthProperty());
        notesAnimation.heightProperty().bind(songpage.prefHeightProperty());
        notesAnimation.widthProperty().bind(songpage.prefWidthProperty());

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
    public VBox getVbox() {
        return songpage;
    }
    public NotesAnimation getAnimation()
    {
        return notesAnimation;
    }
    public ReadOnlyObjectProperty<Duration> getTimeProperty()
    {
        return notesAnimation.getTimeProperty();
    }

    public void setHeight(double h)
    {
        height.set(h);
    }
    public void setWidth(double w)
    {
        width.set(w);
    }
    
    public void update()
    {
        keyboard.update();
        notesAnimation.update();
    }
    
    public void play()
    {
        notesAnimation.play();
    }
    public void pause()
    {
        notesAnimation.pause();
    }
}