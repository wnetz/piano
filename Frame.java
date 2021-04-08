import javax.swing.*;

public class Frame extends JFrame
{
    Graphic graphic = new Graphic();

    public Frame()
    {
        this.setSize(420,420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        this.setVisible(true);
    }
}
