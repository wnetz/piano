
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Graphics.SongPage;
import MIDI.ProcessSong;
import MIDI.Parsing.Parser;
import MIDI.Parsing.Song;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PianoProject extends Application
{
    Stage window;

    @Override
    public void start(Stage window)
    {     

        Parent root;
        try {
            
            String filePathString = "Graphics/FXML/PracticeWindow.fxml";
            FXMLLoader l = new FXMLLoader(getClass().getResource(filePathString));

            File f = new File(filePathString);
            if(f.exists()) { 
                System.out.println("File exists");
                System.out.println(f.getAbsoluteFile());
            }
            root = l.load();
            window.setScene(new Scene(root));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        window.setTitle("pp");
        window.show();
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
}