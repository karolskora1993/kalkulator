package kalkulator.views;

import java.awt.*;
import javax.swing.*;


/**
 * Klasa reprezentująca panel wprowadzania zadania
 *
 * @author Karol
 */
public class ResultPanel extends JPanel
{

    private BackgroundComponent bgComponent;
    private final Frame parent;

    /**
     *
     * @param parent Okno zawierające panel
     */
    public ResultPanel(Frame parent)
    {
        this.parent=parent;
        initializeComponent();
    }

    /**
     * Funkcja inicjalizująca komponenty
     */
    private void initializeComponent()
    {
        setSize(FrameSize.getWidth(), FrameSize.getHeight());
        setLayout(new BorderLayout());
        
        bgComponent=new BackgroundComponent("loginBG.jpg");
        bgComponent.setLayout(new BorderLayout());
        
        add(bgComponent);
    }
}