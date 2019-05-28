/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.graphique;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import projet.main.Histogram;
import projet.main.ImagePNG;
import projet.main.ImageView;

public class WindowManager {

    private Container container;
    private JPanel panel;
    private Window windowID;

    public enum Window {

        MAIN("main"),
        SHOWIMAGE("Affichage image"),
        HISTOGRAMME("Hsitogramme des couleurs"),
        FILTRAGE("Filtrage");

        private String name;

        Window(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public WindowManager(Window window) {
        this.windowID = window;
        createWindow(window);
    }

    public WindowManager() {
        //si l'on veut creer la fenetre à part (this.createWindow(window))
    }
    
    
    public void createWindow(Window window) {
        //windowsID est une enum
        this.windowID = window;

        if (window == Window.MAIN) {
            MainWindow mainWindow = new MainWindow();

        } else if (window == Window.SHOWIMAGE) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("image.png"));
            } catch (IOException ex) {
                System.err.println("ERREUR : echec ouverture d'image");
            }
            ImagePNG imagePNG = new ImagePNG(image);
            BufferedImage bfIm = imagePNG.createBufferedImage();
            ImageView view = new ImageView("Canal vert", bfIm);
            view.setVisible(true);


        }else if (window == Window.HISTOGRAMME) {
            
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("image.png"));
            } catch (IOException ex) {
                System.err.println("ERREUR : echec ouverture d'image");
            }
            ImagePNG imagePNG = new ImagePNG(image);
            Histogram histo = new Histogram(imagePNG);
            histo.showHistogram();

            

        }else if(window == Window.FILTRAGE){
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("image.png"));
            } catch (IOException ex) {
                System.err.println("ERREUR : echec ouverture d'image");
            }
            ImagePNG imagePNG = new ImagePNG(image);
            imagePNG = imagePNG.filtrage("contour");
            BufferedImage bfIm = imagePNG.createBufferedImage();
            ImageView view = new ImageView(null, bfIm);
            view.setVisible(true);
        }
        

    }

}
