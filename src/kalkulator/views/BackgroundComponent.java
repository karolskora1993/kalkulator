package kalkulator.views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import kalkulator.models.ScaleImage;

/**
 * Klasa reprezentująca komponent z tłem
 *
 * @author Karol
 */
public class BackgroundComponent extends JComponent
{
    private String fileName;
    public BackgroundComponent(String fileName){
        this.fileName=fileName;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        Image image = new ImageIcon("images/"+fileName).getImage();
        Image backgroundImage = ScaleImage.getScaledImage(image, (int) FrameSize.getWidth(), (int) FrameSize.getHeight());

        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension((int) FrameSize.getWidth(), (int) FrameSize.getHeight());
    }
}