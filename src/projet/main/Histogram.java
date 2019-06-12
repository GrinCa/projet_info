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
    private int hauteur = 170;
    private double maxRed;
    private double maxGreen;
    private double maxBlue;

    public Histogram(ImagePNG imagePNG) {
        this.imagePNG = imagePNG;
        this.histogram = getColorHistogram();
        this.red = histogram[0];
        this.green = histogram[1];
        this.blue = histogram[2];
        this.normalise();
        setTitle("Histograme des couleurs");
        setBounds(20, 20, 360, 800);
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

    public void save() {

        try {
            PrintWriter file = new PrintWriter(
                    new FileWriter("histogram.txt"));
            for (int i = 0; i < this.red.length; i++) {
                file.print(red[i] + "," + green[i] + "," + blue[i] + ",");
            }
            file.close();
        } catch (IOException ex) {
            System.err.println("ERREUR : Histogram");
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int xg = 50;
        int xd = 300;
        int decalage = 250;
        int hauteurGraph = 210;
        g.drawLine(xg, decalage, xd+20, decalage);
        g.drawLine(xg, decalage, xg, decalage-hauteurGraph);
        
        g.drawLine(xd+20, decalage, xd+10, decalage+10);
        g.drawLine(xd+20, decalage, xd+10, decalage-10);
        
        g.drawString("Canal rouge", 120, decalage+15);
        g.drawString(""+(int)maxRed, xg-35, decalage-(int)(0.75*hauteurGraph));
        //
        g.drawLine(xg, 2*decalage, xd+20, 2*decalage);
        g.drawLine(xg, 2*decalage, xg, 2*decalage-hauteurGraph);
        
        g.drawLine(xd+20, 2*decalage, xd+10, 2*decalage+10);
        g.drawLine(xd+20, 2*decalage, xd+10, 2*decalage-10);
        
        g.drawString("Canal vert", 120, 2*decalage+15);
        g.drawString(""+(int)maxGreen, xg-35, 2*decalage-(int)(0.75*hauteurGraph));
        //
        g.drawLine(xg, 3*decalage, xd+20, 3*decalage);
        g.drawLine(xg, 3*decalage, xg, 3*decalage-hauteurGraph);
        
        g.drawLine(xd+20, 3*decalage, xd+10, 3*decalage+10);
        g.drawLine(xd+20, 3*decalage, xd+10, 3*decalage-10);
        
        g.drawString("Canal bleu", 120, 3*decalage+15);
        g.drawString(""+(int)maxBlue, xg-35, 3*decalage-(int)(0.75*hauteurGraph));

        for (int i = 0; i < red.length; i++) {
            g.drawLine(xg+i, decalage, xg+i, decalage-(int)red[i]);
            g.drawLine(xg+i, 2*decalage, xg+i, 2*decalage-(int)green[i]);
            g.drawLine(xg+i, 3*decalage, xg+i, 3*decalage-(int)blue[i]);
        }
    }

    private void normalise() {
        maxRed = Histogram.max(red);
        maxGreen = Histogram.max(green);
        maxBlue = Histogram.max(blue);
        for (int i = 0; i < this.red.length; i++) {
            red[i] = hauteur * red[i] / maxRed;
            green[i] = hauteur * green[i] / maxGreen;
            blue[i] = hauteur * blue[i] / maxBlue;
        }
    }

    private static double max(double[] tab) {
        double max = tab[0];
        for (int i = 1; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
            }
        }
        return max;
    }

}
