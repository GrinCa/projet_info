package projet.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import projet.graphique.Fenetre;
import projet.graphique.WindowManager;
import projet.graphique.WindowManager.Window;

public class MainProjet extends JFrame {

    public static void main(String args[]) throws IOException {

        /*WindowManager windowManager = new WindowManager();
        windowManager.createWindow(Window.MAIN);
        BufferedImage image = ImageIO.read(new File("image.png"));
        ImagePNG imagePNG = new ImagePNG(image);
        BufferedImage image2 = ImageIO.read(new File("image2.png"));
        ImagePNG imagePNG2 = new ImagePNG(image2);
        ImagePNG assemble = imagePNG.insertImage(imagePNG2);
        

        
        ImagePNG noirEtBlanc = imagePNG.binairisation(80).filtrage("contour");
        ImagePNG im = imagePNG.getGrey().filtrage("contour");
        Fenetre fen = new Fenetre(assemble.createBufferedImage());
        fen.setVisible(true);*/
        
        System.out.println("ok");
        
  
        
    }

}
