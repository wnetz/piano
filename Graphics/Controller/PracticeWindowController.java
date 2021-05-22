package Graphics.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;

import Graphics.SongPage;
import MIDI.ProcessSong;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private JFXButton play;
    @FXML
    private JFXButton left;
    @FXML
    private JFXButton right;
    @FXML
    private Separator songFieldSeparator;
    

    SongPage songPage;

    EventHandler<MouseEvent> playPause = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent e)
        {
            if(play.isArmed())
            {
                play.disarm();
                play.setText("pause");
                System.out.println("pause");
            }
            else
            {
                play.arm();
                play.setText("play");
                System.out.println("play");
            }
        }
    };

    public PracticeWindowController()
    {
        Parser parser = new Parser();         
        String filePath = ".\\MIDI\\Parsing\\";
        Song song = parser.parse(filePath + "Watashi_no_Uso.mscx");//scan.nextLine()
        ProcessSong ps = new ProcessSong(song);       
        songPage = new SongPage(ps.getSong());
        songPage.update();   
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        ChangeListener<Number> resize = (observable,oldvalue,newvalue) -> {
            songPage.update();
            System.out.println(songField.heightProperty().get()*4/5.0 + " " + songPage.getHeight() + " " + songField.getHeight());
        };

        play.setText("play");
        left.setText("left");
        right.setText("right");
        
        songPage.heightProperty().bind(songField.heightProperty());
        songPage.widthProperty().bind(songField.widthProperty());
        songFieldSeparator.prefHeightProperty().bind(songField.heightProperty());


        window.widthProperty().addListener(resize);
        window.heightProperty().addListener(resize);

        songField.getChildren().addAll(songPage.getVbox());
        //songPage.update();
        play.addEventHandler(MouseEvent.MOUSE_CLICKED, playPause);
    }
    
}
