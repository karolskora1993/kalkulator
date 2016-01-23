package kalkulator.views;

import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameSize
{

    public static int WIDTH;
    public static int HEIGHT;

    static {
        Toolkit myToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = myToolkit.getScreenSize();
        WIDTH = (int) (screenSize.width * 0.7);
        HEIGHT = (int) (screenSize.height * 0.7);
    }

    public static int getWidth()
    {
        updateSize();
        return WIDTH;
    }

    public static int getHeight()
    {
        updateSize();
        return HEIGHT;
    }
    
    public static void updateSize(){
        Toolkit myToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = myToolkit.getScreenSize();
        WIDTH = (int) (screenSize.width * 0.7);
        HEIGHT = (int) (screenSize.height * 0.7);
        
    }
}
