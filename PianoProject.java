
import Graphics.SongPage;
import MIDI.ProcessSong;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PianoProject extends Application
{
    Stage window;

    @Override
    public void start(Stage primaryStage)
    {
        Parser parser = new Parser();         
        String filePath = ".\\MIDI\\Parsing\\";
        Song song = parser.parse(filePath + "Watashi_no_Uso.mscx");//scan.nextLine()
        ProcessSong ps = new ProcessSong(song);

        window = primaryStage;       
        SongPage songPage = new SongPage(ps.getSong());
        Scene scene = new Scene(songPage.getVbox(),600,600);
        songPage.heightProperty().bind(scene.heightProperty());
        songPage.widthProperty().bind(scene.widthProperty());
        
        ChangeListener<Number> resize = (observable,oldvalue,newvalue) -> {
            songPage.update();
        };
        scene.widthProperty().addListener(resize);
        scene.heightProperty().addListener(resize);

        window.setTitle("pp");
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
}