package Graphics.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

public class DrawerController implements Initializable
{
    @FXML
    private JFXHamburger ham;
    @FXML 
    private JFXDrawer drawer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try
        {
            VBox vbox = FXMLLoader.load(getClass().getResource("../FXML/SideMenu.fxml"));
            drawer.setSidePane(vbox);
        }
        catch(IOException e)
        {
            Logger.getLogger(DrawerController.class.getName()).log(Level.SEVERE, null, e);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(ham);
        ham.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->
        {
            transition.setRate(transition.getRate()*-1);
            transition.play();

            if(drawer.isOpened())
            {
                drawer.close();
            }
            else
            {
                drawer.open();
            }
        });
    }
}