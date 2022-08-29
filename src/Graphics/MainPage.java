package Graphics;

import Graphics.Controll.Slider;

import java.awt.*;
import javax.swing.*;

public class MainPage extends Canvas
{
    public MainPage(int width, int height, String title, PianoProject pp)
    {
        JFrame frame = new JFrame(title);
        JSlider slider = new JSlider(0, 10);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        Slider time = new Slider(slider);
        //slider.setUI(time);
        slider.setValue(11);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add("North",slider);
        frame.add("Center",pp);
        frame.setVisible(true);
        pp.start();
    }
}