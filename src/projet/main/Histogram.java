package projet.main;

import java.awt.Graphics;
import java.awt.Panel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;

/**
 *
 * @author jecobich
 */
public class Histogram extends JFrame {

    private Panel panel;
    private double[] red;
    private double[] green;
    private double[] blue;
    private double[][] histogram;
    private ImagePNG imagePNG;

    public Histogram(ImagePNG imagePNG) {
        this.imagePNG = imagePNG;
        this.histogram = getColorHistogram();
        this.red = histogram[0];
        this.green = histogram[1];
        this.blue = histogram[2];
    }

   
    public double[][] getColorHistogram() {
        Pixel[][] image = imagePNG.getImage();
        double[][] histogrammeCouleurRGB = new double[3][256];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                histogrammeCouleurRGB[0][image[i][j].getValue255("red")] += 1d;
                histogrammeCouleurRGB[1][image[i][j].getValue255("green")] += 1d;
                histogrammeCouleurRGB[2][image[i][j].getValue255("blue")] += 1d;
            }
        }

        return histogrammeCouleurRGB;
    }

    public void showHistogram() {
        setSize(300, 900);
        setVisible(true);
    }
    
    public void save() {

        try {
            PrintWriter file = new PrintWriter(
                    new FileWriter("histogram.txt"));
            for (int i = 0; i < this.red.length; i++) {
                file.print(red[i] + "," + green[i] + "," + blue[i] + ",");
            }
            file.close();
        } catch (IOException ex) {
            System.err.println("ERREUr : Histogram");
        }

    }

    @Override
    public void paint(Graphics g) {

       
    }

}
