package kalkulator.models;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Klasa przeskalowująca obrazek do porządanych rozmiarów
 *
 * @author Karol
 */
public class ScaleImage
{

    /**
     * Funkcja statyczna służąca do przeskalowywania obrazków do porządanych
     * rozmiarów
     *
     * @param srcImg wyjściowy obraz
     * @param width porządana szerokość
     * @param height porządana wysokość
     * @return przeskalowany obraz
     * 
     */
    public static Image getScaledImage(Image srcImg, int width, int height)
    {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();
        return new ImageIcon(resizedImage).getImage();
    }
}
