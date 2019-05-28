/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.graphique;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import projet.main.Histogram;
import projet.main.ImagePNG;

/**
 *
 * @author jecobich
 */
public class Fenetre extends JFrame{
    
    private BufferedImage image;
    private Histogram histo;
    
    public Fenetre(BufferedImage image){
        this.image = image;
        setSize(new Dimension(image.getWidth(), image.getHeight()));
        histo = new Histogram(new ImagePNG(image));
        /*Container container = this.getContentPane();
        container.add(histo);
        histo.repaint();*/
    }
    
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, rootPane);
    }
}
