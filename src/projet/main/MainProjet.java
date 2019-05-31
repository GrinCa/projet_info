package projet.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import projet.graphique.Affichage;



public class MainProjet extends JFrame {

    public static void main(String args[]) throws IOException {

        /*WindowManager windowManager = new WindowManager();
        windowManager.createWindow(Window.MAIN);*/
        BufferedImage image = ImageIO.read(new File("image.png"));
        ImagePNG imagePNG = new ImagePNG(image);
        
        Affichage aff = new Affichage();
        aff.setVisible(true);
        
        System.out.println("hello world!");
        

        System.out.println("Hello world!");
  
        
    }

}
