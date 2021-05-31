
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PianoProject extends Application//means that start is automaticly called by launch(args) in main
{    
    private File file;
    private FXMLLoader loader;
    private Parent root;
    private String filePath;

    public static void main(String[] args) 
    {
        System.out.println("PianoProject>");
        launch(args);
        System.out.println("PianoProject<");
    }
    @Override
    public void start(Stage window)
    {   
        System.out.println("PianoProject: start>");
        try 
        {            
            filePath = "Graphics/FXML/PracticeWindow.fxml";//get window
            loader = new FXMLLoader(getClass().getResource(filePath));
            file = new File(filePath);

            if(file.exists()) 
            { 
                System.out.println("PianoProject: File exists");
                System.out.println("PianoProject: " + file.getAbsoluteFile());
            }

            root = loader.load();//load window
            window.setScene(new Scene(root)); 

        } catch (IOException e)//for when file does not exist 
        {
            e.printStackTrace();
        }

        window.setTitle("pp");
        window.show();
        System.out.println("PianoProject: start<");
    }
}