package Graphics.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Graphics.NotesAnimation;
import Graphics.SongPage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
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
import MIDI.ProcessSong;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;

public class PracticeWindowController implements Initializable
{
    private double duration;    
    @FXML
    private FontAwesomeIconView playIcon;
    @FXML
    private HBox bottomBar;
    @FXML
    private ImageView leftIcon;  
    @FXML
    private ImageView rightIcon;
    @FXML
    private JFXButton left;
    @FXML
    private JFXButton right;
    @FXML
    private JFXSlider speed;
    @FXML
    private JFXSlider time;
    @FXML
    private JFXToggleNode play;
    private NotesAnimation notesAnimation;
    @FXML
    private Separator playLRSeparator;
    @FXML
    private Separator speedPlaySeparator;
    @FXML
    private Separator songFieldSeparator;
    private Song song;
    private SongPage songPage;
    @FXML
    private StackPane window;
    @FXML
    private VBox songField;
    @FXML
    private VBox topBar;    

    public PracticeWindowController()
    {
        System.out.println("PracticeWindowController");
        Parser parser = new Parser();         
        String filePath = ".\\MIDI\\Parsing\\";
        song = parser.parse(filePath + "Watashi_no_Uso.mscx");//place holder
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        System.out.println("PracticeWindowController: initialize>");

        ProcessSong ps = new ProcessSong(song); 
        duration = ps.getDuration();
        songPage = new SongPage(ps.getSong(),time);
        songPage.update();  
        notesAnimation = songPage.getAnimation();      

        ChangeListener<Number> resize = (observable,oldvalue,newvalue) ->  
        {
            System.out.println("PracticeWindowController: initialize: resize>");
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

            //System.out.println(playIcon.getSize() + " " + topBar.getPrefHeight() + " " + songField.getHeight() + " " +  bottomBar.getPrefHeight() + " " + (topBar.getHeight() + songFieldSeparator.getHeight() +  bottomBar.getHeight()));
            System.out.println("PracticeWindowController: initialize: resize<");
        };
        ChangeListener<Boolean> playPause = (observable,oldvalue,newvalue) -> 
        {
            System.out.println("PracticeWindowController: initialize: playPause>");
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
            System.out.println("PracticeWindowController: initialize: playPause<");
        };
        
        songPage.getHeightProperty().bind(songField.heightProperty());
        songPage.getWidthProperty().bind(songField.widthProperty());
        songFieldSeparator.prefHeightProperty().bind(songPage.getHeightProperty());

        this.initializeTime();

        playIcon.prefHeight(play.getPrefHeight());   
        playIcon.prefWidth(play.getPrefHeight());     

        window.widthProperty().addListener(resize);
        window.heightProperty().addListener(resize);

        play.selectedProperty().addListener(playPause);

        songField.getChildren().addAll(songPage.getVbox());
        System.out.println("PracticeWindowController: initialize<");
    }
    private void initializeTime()
    {
        System.out.println("PracticeWindowController: initializeTime>");
        ChangeListener<Number> timeChange = (observable,oldvalue,newvalue) -> 
        {
            //System.out.println("PracticeWindowController: initializeTime: timeChange>");
            if(notesAnimation.getPause())
            {
                notesAnimation.setTime(new Duration(time.getValue()*60000));
                notesAnimation.update();
            }
            //System.out.println("PracticeWindowController: initializeTime: timeChange<");
        };

        time.setMin(0);
        time.setMax(duration);
        time.setShowTickMarks(true);
        time.setMajorTickUnit(duration/song.getTop().size());
        time.setMinorTickCount(0);
        time.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0) 
            {
                System.out.println("PracticeWindowController: Time: MousePressed>");
                playIcon.setGlyphName("PAUSE_CIRCLE");
                songPage.pause(); 
                System.out.println("PracticeWindowController: Time: MousePressed<");           
            }            
        });
        time.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0) 
            {
                System.out.println("PracticeWindowController: Time: MouseReleased>");
                playIcon.setGlyphName("PLAY_CIRCLE");
                notesAnimation.setTime(new Duration(time.getValue()*60000));
                System.out.println(time.getValue()); 
                notesAnimation.update();
                notesAnimation.play();
                System.out.println("PracticeWindowController: Time: MouseReleased<");
            }
        });
        time.valueProperty().addListener(timeChange);
        System.out.println("PracticeWindowController: initializeTime<");
    } 
}
