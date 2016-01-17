package kalkulator.views;

import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameSize
{

    public static final int WIDTH;
    public static final int HEIGHT;

    static {
        Toolkit myToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = myToolkit.getScreenSize();
        WIDTH = (int) (screenSize.width * 0.5);
        HEIGHT = (int) (screenSize.height * 0.5);
    }

    public static int getWidth()
    {
        return WIDTH;
    }

    public static int getHeight()
    {
        return HEIGHT;
    }
}
