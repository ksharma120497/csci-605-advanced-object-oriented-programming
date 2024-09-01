// original from: http://rosettacode.org/wiki/Pi_set#Java
// modified for color
package HW10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.Random;
import java.io.*;
import javax.imageio.ImageIO;

public class Pi extends JFrame {

    public final int LENGTH_OF_SQUARE = 3;
    public final int LENGTH 	       = 330;
    public final int LENGTH_OF_WINDOW = LENGTH * LENGTH_OF_SQUARE;
    BufferedImage theImage;

    private String fileName = null;

    Reader input;

    public Pi() {
        super("Pi");
        setBounds(100, 100, LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            input = new InputStreamReader(System.in);
        } catch ( Exception e )	{
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void saveImage(BufferedImage theImage)	{
        try {
            String suffix = "png";
            File outputfile = new File("output." + suffix);
            ImageIO.write(theImage, suffix, outputfile);
        } catch (Exception e )	{
            e.printStackTrace();
        }
    }

    public void createImage()	{
        theImage = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Random aRandom = new Random();

        for (int y = 0; y < getHeight(); y += LENGTH_OF_SQUARE) {
            for (int x = 0; x < getWidth(); x += LENGTH_OF_SQUARE) {
                //int digit = aRandom.nextInt(0, LENGTH_OF_WINDOW );
                //new PiThread(x,y, digit, this).start();
            }
        }
        repaint();
        saveImage(theImage);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(theImage, 0, 0, this);
    }


    public static void main(String[] args) {
        String fileName = null;
        fileName = "output.png";
        Pi aPi = new Pi();
        aPi.setVisible(true);
        aPi.createImage();
    }

}

class PiThread extends Thread{

    int xOrig;

    int yOrig;

    int digit;

    Pi pi;

    public PiThread(int x, int y, int digit, Pi pi){
        this.xOrig = x;
        this.yOrig = y;
        this.digit = digit;
        this.pi = pi;
    }

    @Override
    public void run() {
        int red = Color.RED.getRGB();
        int blue = Color.BLUE.getRGB();
            for (int x = 0; x < pi.LENGTH_OF_SQUARE; x ++ )
                for (int y = 0; y < pi.LENGTH_OF_SQUARE; y ++ ) {
                    pi.theImage.setRGB(xOrig + x, yOrig + y, digit % 2 == 0 ? red : blue);
            }
        }
}