package Graphics.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleNode;

import Graphics.NotesAnimation;
import Graphics.SongPage;
import MIDI.ProcessSong;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PracticeWindowController implements Initializable
{
    @FXML
    private StackPane window;
    @FXML
    private JFXSlider time;
    @FXML
    private VBox songField;
    @FXML
    private VBox topBar;
    @FXML
    private HBox bottomBar;
    @FXML
    private JFXSlider speed;
    @FXML
    private JFXToggleNode play;
    @FXML
    private JFXButton left;
    @FXML
    private JFXButton right;
    @FXML
    private Separator songFieldSeparator;
    @FXML
    private Separator speedPlaySeparator;
    @FXML
    private Separator playLRSeparator;
    @FXML
    private FontAwesomeIconView playIcon;
    @FXML
    private ImageView rightIcon;
    @FXML
    private ImageView leftIcon;
    
    private double duration;
    private Song song;
    private SongPage songPage;
    private NotesAnimation notesAnimation;

    public PracticeWindowController()
    {
        Parser parser = new Parser();         
        String filePath = ".\\MIDI\\Parsing\\";
        song = parser.parse(filePath + "Watashi_no_Uso.mscx");//scan.nextLine()
        
         
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        ProcessSong ps = new ProcessSong(song); 
        duration = ps.getDuration();
        songPage = new SongPage(ps.getSong(),time);
        songPage.update();  
        notesAnimation = songPage.getAnimation();      

        ChangeListener<Number> resize = (observable,oldvalue,newvalue) ->  
        {
            songPage.update();
            songField.setPrefHeight(window.getHeight()*SongPage.PERCENT_HEIGHT);
            songField.setPrefWidth(window.getWidth());

            topBar.setPrefHeight(window.getHeight()*((1-SongPage.PERCENT_HEIGHT)/2.0));
            bottomBar.setPrefHeight(window.getHeight()*((1-SongPage.PERCENT_HEIGHT)/2.0));

            speed.setPrefWidth(window.getWidth()/5.0);
            speed.setPrefHeight(bottomBar.getPrefHeight());

            play.setPrefWidth(window.getWidth()/10.0);
            play.setPrefHeight(bottomBar.getPrefHeight());

            left.setPrefWidth(bottomBar.getPrefHeight());
            left.setPrefHeight(bottomBar.getPrefHeight());
    
            right.setPrefWidth(bottomBar.getPrefHeight());
            right.setPrefHeight(bottomBar.getPrefHeight());

            playIcon.setSize("" + (bottomBar.getPrefHeight()*4/5));  
            rightIcon.setFitHeight(bottomBar.getPrefHeight()*4/5);
            rightIcon.setFitWidth(bottomBar.getPrefHeight()*4/5);
            leftIcon.setFitHeight(bottomBar.getPrefHeight()*4/5);
            leftIcon.setFitWidth(bottomBar.getPrefHeight()*4/5);

            speedPlaySeparator.setPrefWidth(window.getWidth()/2.0 - window.getWidth()/5.0 - window.getWidth()/10.0/2.0);
            playLRSeparator.setPrefWidth(window.getWidth()/2.0 - window.getWidth()/10.0/2.0 - bottomBar.getPrefHeight() - bottomBar.getPrefHeight());

            System.out.println(playIcon.getSize() + " " + topBar.getPrefHeight() + " " + songField.getHeight() + " " +  bottomBar.getPrefHeight() + " " + (topBar.getHeight() + songFieldSeparator.getHeight() +  bottomBar.getHeight()));
        };
        ChangeListener<Boolean> playPause = (observable,oldvalue,newvalue) -> 
        {
            
            if(newvalue)
            {
                playIcon.setGlyphName("PAUSE_CIRCLE");
                notesAnimation.pause();                
            }
            else
            {
                playIcon.setGlyphName("PLAY_CIRCLE");
                notesAnimation.play();
            }
        };
        ChangeListener<Number> timeChange = (observable,oldvalue,newvalue) -> 
        {
            if(notesAnimation.getPause())
            {
                notesAnimation.setTime(new Duration(time.getValue()*60000));
                notesAnimation.update();
            }
        };
        
        songPage.heightProperty().bind(songField.heightProperty());
        songPage.widthProperty().bind(songField.widthProperty());
        songFieldSeparator.prefHeightProperty().bind(songPage.heightProperty());

        time.setMin(0);
        time.setMax(duration);
        time.setShowTickMarks(true);
        time.setMajorTickUnit(duration/song.getTop().size());
        time.setMinorTickCount(0);
        time.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0) {
                playIcon.setGlyphName("PAUSE_CIRCLE");
                songPage.pause();            
            }            
        });
        time.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0) {
                playIcon.setGlyphName("PLAY_CIRCLE");
                notesAnimation.setTime(new Duration(time.getValue()*60000));
                System.out.println(time.getValue()); 
                notesAnimation.update();
                notesAnimation.play();
            }
        });
        time.valueProperty().addListener(timeChange);

        playIcon.prefHeight(play.getPrefHeight());   
        playIcon.prefWidth(play.getPrefHeight());     

        window.widthProperty().addListener(resize);
        window.heightProperty().addListener(resize);

        play.selectedProperty().addListener(playPause);

        songField.getChildren().addAll(songPage.getVbox());
    }
    
}