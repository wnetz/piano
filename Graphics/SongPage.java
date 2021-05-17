package Graphics;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.VBox;

public class SongPage
{
    private DoubleProperty height, width;
    private Keyboard keyboard;
    private NotesAnimation notesAnimation;
    private VBox songpage;

    public SongPage(ArrayList<Notes> notes)
    {           
        height = new SimpleDoubleProperty(this,"height",0);
        width = new SimpleDoubleProperty(this,"width",0);
        keyboard = new Keyboard();
        notesAnimation = new NotesAnimation(notes);
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
            songpage.setPrefHeight(height.get());
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
    public void setHeight(double h)
    {
        height.set(h);
    }
    public void setWidth(double w)
    {
        width.set(w);
    }
    public VBox getVbox() {
        return songpage;
    }
    public void update()
    {
        keyboard.update();
        notesAnimation.update();
    }
    
}